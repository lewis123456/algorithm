import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;

/**
 * 有n个数据文件，内容格式每行包含用户访问 url以及用户 id
 * example：
 * www.taobao.com/item?id=1	123
 * www.taobao.com/search?key=apple	234
 * www.tmall.com/item?id=2	123
 * 所有文件在一个文件夹目录下
 * 请编写一段代码，对n个文件做处理，统计出每个一级域名（比如：www.taobao.com）下 uv排名top10的url以及uv值，要求需多线程并行处理
 * 同时考虑扩展性，统计需考虑一级域名外还有二级域名的统计需求，以及除uv外会有pv统计需求
 * 代码以函数形式提供，最终需提供可运行的java main，数据文件可自行mock。
 */

public class TH0003 {
    //数据文件所在的目录
    private final String FILE_PATH = "C:\\Users\\lewis\\IdeaProjects\\algorithm\\resources\\TH0003";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("top(排名前top个), level(第level级域名的统计), type(1代表uv,2代表pv)");
            int top = scanner.nextInt();
            int level = scanner.nextInt();
            int type = scanner.nextInt();
            new TH0003().getSort(top, level, type);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param top   排名数
     * @param level 域名级别，1 级域名，2 级域名
     * @param type  是否去重，1 去重uv，2 不去重pv
     */
    public void getSort(int top, int level, int type) throws Exception {
        File file = new File(FILE_PATH);
        File[] files = file.listFiles();
        int n = files.length;
        //todo 线程池的大小与数据量有关
        int count = n;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        //todo 主线程等待子线程完成
        CountDownLatch latch = new CountDownLatch(n);
        //保存每个url及其用户访问的pv,uv
        ConcurrentHashMap<String, MyNode> concurrentHashMap = new ConcurrentHashMap<>();
        //第level级域名的集合
        Set<String> domains = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int index = i;
            executorService.submit(() -> {
                try {
                    FileReader fileReader = new FileReader(files[index]);
                    BufferedReader reader = new BufferedReader(fileReader);
                    //todo 如果文件太大，需要分次读取
                    while (true) {
                        String line = reader.readLine();
                        if (null == line) {
                            break;
                        }
                        String[] params = line.split("\\s+");
                        //去掉请求参数的url
                        String url = params[0].split("\\?")[0];

                        //第level级域名
                        String[] urls = url.split("/");
                        String realUrl = "";
                        if (urls.length >= level) {
                            for (int j=0; j<level; j++) {
                                if (j != level - 1) {
                                    realUrl += (urls[j] + "/");
                                } else {
                                    realUrl += urls[j];
                                }
                            }
                            domains.add(realUrl);
                        }

                        //访问的用户
                        String user = params[1];

                        if (concurrentHashMap.keySet().contains(url)) {
                            MyNode myNode = concurrentHashMap.get(url);
                            myNode.allPut(user);
                            myNode.onlyPut(user);
                            concurrentHashMap.put(url, myNode);
                        } else {
                            MyNode myNode = new MyNode();
                            myNode.onlyPut(user);
                            myNode.allPut(user);
                            concurrentHashMap.put(url, myNode);
                        }
                    }
                    latch.countDown();
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
        latch.await();
        executorService.shutdown();
        //所有的url
        Set<String> allUrl = concurrentHashMap.keySet();
        if (null == allUrl) {
            System.out.println("no fitted url");
        }

        //排序每个第level级域名下的url
        for (String item : domains) {
            HashMap<String, Integer> urlAndCount = new HashMap<>();
            //筛一遍在domain下的域名
            for (String temp : allUrl) {
                if (temp.contains(item)) {
                    int addCount = 0;
                    if (1 == type) {
                        addCount = concurrentHashMap.get(temp).getOnly().size();
                    }else if (2 == type) {
                        addCount = concurrentHashMap.get(temp).getAll().size();
                    }else {
                        System.out.println("error");
                        return;
                    }

                    if (urlAndCount.containsKey(temp)) {
                        addCount += urlAndCount.get(temp);
                        urlAndCount.put(temp, addCount);
                    }  else {
                        urlAndCount.put(temp, addCount);
                    }
                }
            }
            //排序
            List<UrlAndCount> list = new ArrayList<>();
            for (String url : urlAndCount.keySet()) {
                list.add(new UrlAndCount(url, urlAndCount.get(url)));
            }
            Collections.sort(list);
            int size = list.size();
            System.out.println(item);
            for (int i=0; i<top; i++) {
                if (i >= size) {
                    break;
                }
                System.out.println("[" + (i+1) + "] " + list.get(i).getUrl() + ", " + list.get(i).getCount());
            }
        }
    }
}

class MyNode {
    //us不能重复
    private Set<String> only;
    //pv重复
    private List<String> all;

    public MyNode() {
        only = new HashSet<String>();
        all = new ArrayList<>();
    }

    public Set<String> getOnly() {
        return only;
    }

    public void setOnly(Set<String> only) {
        this.only = only;
    }

    public List<String> getAll() {
        return all;
    }

    public void setAll(List<String> all) {
        this.all = all;
    }

    public void onlyPut(String str) {
        only.add(str);
    }

    public void allPut(String str) {
        all.add(str);
    }
}

class UrlAndCount implements Comparable<UrlAndCount>{
    private String url;
    private int count;

    UrlAndCount(String url, int count) {
        this.url = url;
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int compareTo(UrlAndCount o) {
        if (count > o.count) {
            return -1;
        } else if (count < o.count) {
            return 1;
        } else {
            return 0;
        }
    }
}

