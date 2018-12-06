import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeWork {
    public String homework_name;
    public int length;

    public HomeWork(String homework_name, int length) {
        this.homework_name = homework_name;
        this.length = length;
    }

    /**
     * 创建作业
     * @return
     */
    public static List<HomeWork> create_homework(){
        List<HomeWork> homeWorklist = new ArrayList<HomeWork>();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入作业个数：");
        int number = input.nextInt();
        input.nextLine();
        for (int i=0;i<number;i++){
            System.out.println("请输入作业名：");
            String homeworkname = input.nextLine();
            System.out.println("请输入作业的长度:");
            int length = input.nextInt();
            input.nextLine();//去回车
            HomeWork h = new HomeWork(homeworkname,length);
            homeWorklist.add(h);
        }
        return homeWorklist;
    }

    /**
     * 显示作业
     */
    public static void display_homework(List<HomeWork> homeWorkList){
        for (int i=0;i<homeWorkList.size();i++){
            System.out.printf("作业名：%s  作业长度：%d\n",homeWorkList.get(i).homework_name,homeWorkList.get(i).length);
        }
    }
}
