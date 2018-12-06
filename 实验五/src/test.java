import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class test {

    public static void main(String [] args){
        Service service = new Service();
        //初始化分区表并显示
        //未分配分区表
        List<Block> blocklist = new ArrayList<>();
        //已分配分区表
        List<Block> finish_distribute_blocklist = new ArrayList<>();
        Block block0 = new Block(0, 50, "OS");
        Block block1 = new Block(50, 50, "free");
        Block block2 = new Block(100, 50, "A");
        Block block3 = new Block(150, 100, "free");
        Block block4 = new Block(250, 100, "B");
        Block block5 = new Block(350, 100, "free");
        blocklist.add(block1);
        blocklist.add(block3);
        blocklist.add(block5);
        finish_distribute_blocklist.add(block0);
        finish_distribute_blocklist.add(block2);
        finish_distribute_blocklist.add(block4);
        System.out.println("未分配表和已分配表初始化为：");
        Block.displayBlock(blocklist,finish_distribute_blocklist);
        //初始化创建作业并显示
//        List<HomeWork> homeWorklist = new ArrayList<>();
        List<HomeWork> homeWorklist = HomeWork.create_homework();
        HomeWork.display_homework(homeWorklist);

        //根据菜单提示进行相应的操作
        boolean run = true;
        while(run){
            int number = service.menu();
            switch (number){
                case 0:run = false;break;
                case 1:
                    service.insert(blocklist,finish_distribute_blocklist,homeWorklist);
                    break;
                case 2:
                    service.insert_xunhuan(blocklist,finish_distribute_blocklist,homeWorklist);
                    break;
                case 3:
                    service.deleteBlock(blocklist,finish_distribute_blocklist);
                    break;
                default:System.out.println("输入有误！请重新输入");break;
            }
        }
//        //首次适应分配作业
//        service.insert(blocklist,finish_distribute_blocklist,homeWorklist);
//
//        //循环首次适应
//        service.insert_xunhuan(blocklist,finish_distribute_blocklist,homeWorklist);

        //回收内存，并返回释放后的分区情况
//        List<Block> deleteBlockList = service.deleteBlock(finish_distribute_blocklist);
//        Block.displayBlock(deleteBlockList);   //显示

    }



}
