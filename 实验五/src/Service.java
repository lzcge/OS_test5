import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Service {

    public int menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("--------- 1 : 首次适应   2 : 循环首次适应   3 : 回收内存   0 ：退出 ---------");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("请输入你想进行的操作！");
        int number = input.nextInt();
        return number;
    }

    /**
     * 释放内存
     * @param blockList
     */
    public void deleteBlock(List<Block> blockList,List<Block> finish_distribute_blocklist){
        /**
         * 备份分区表
         */
        List<Block> copy_blockList = new ArrayList<Block>();
        copy_blockList.addAll(blockList);
        Scanner input  = new Scanner(System.in);
        System.out.println("请输入你要回收的作业名:");
        String homework_name = input.nextLine();
        int i = 0;
        Block f = null;
        boolean flag = false;
        for (Iterator fini_block = finish_distribute_blocklist.iterator();fini_block.hasNext();){
            f =  (Block) fini_block.next();
            if(f.status.equals(homework_name)){
                flag = true;
                finish_distribute_blocklist.remove(f);
                break;
            }
        }
        if(f!=null && flag){
            for (i = 0;i<blockList.size();i++){
                Block b1 = copy_blockList.get(i);
                Block b2 =  null;
                if (i+1<=blockList.size()) b2 = copy_blockList.get(i+1);
                if(f.InitialAddress+f.length==b1.InitialAddress)
                {
                    b1.InitialAddress = f.InitialAddress;
                    b1.length = f.length+b1.length;
                    break;
                }
                //与上面空闲合并
                else if((b1.InitialAddress+b1.length==f.InitialAddress) && (f.InitialAddress+f.length!=b2.InitialAddress)){
                    b1.length += f.length;
                    break;
                }
                //与下面空闲合并
                else if((b1.InitialAddress+b1.length < f.InitialAddress) && (f.InitialAddress+f.length==b2.InitialAddress)){
                    b2.InitialAddress = f.InitialAddress;
                    b2.length += f.length;
                    break;
                }
                //上下都合并
                else if((b1.InitialAddress+b1.length == f.InitialAddress) && (f.InitialAddress+f.length==b2.InitialAddress)){
                    b1.length +=(f.length+b2.length);
                    copy_blockList.remove(b2);
                    break;
                }

            }
            //上下都没有相邻空闲，添加此空闲
            if(i>=blockList.size()) copy_blockList.add(new Block(f.InitialAddress,f.length,"free"));
        }else{
            System.out.println("回收失败，请重新输入作业名!");
        }

        Block.displayBlock(copy_blockList,finish_distribute_blocklist);
    }


    /**
     * 首次适应算法
     * @param blockList 空闲分区
     * @param finish_distribute_blocklist  已分配表
     * @param homeWorkList  作业表
     * @return
     */
    public void insert(List<Block> blockList,List<Block> finish_distribute_blocklist, List<HomeWork> homeWorkList){
        HomeWork h = null;
        Block b = null;
        for (Iterator homework = homeWorkList.iterator(); homework.hasNext();){
            h = (HomeWork) homework.next();
            boolean flag = true;
            for (int i = 0;i<blockList.size();i++){
                b = blockList.get(i);
                if (h.length<=b.length ){
                    Block block1 = new Block(b.InitialAddress,h.length,h.homework_name);
                    finish_distribute_blocklist.add(block1);
                    System.out.printf("分配作业 %s 成功！\n",h.homework_name);
                    System.out.printf("分配起始位置为：%d   分配长度为：%d\n",b.InitialAddress,h.length);
                    b.InitialAddress = b.InitialAddress+h.length;
                    b.length = b.length-h.length;
                    Block.displayBlock(blockList,finish_distribute_blocklist);
                    flag = false;
                    break;
                }

            }

            if(flag){
                System.out.println("-----------------------------------------------------------------");
                System.out.printf("分配作业 %s 失败！\n",h.homework_name);
            }
        }

    }


    /**
     * 循环首次适应
     * @param blockList  未分配分区
     * @param homeWorkList 已分配分区
     * @return
     */
    public void insert_xunhuan(List<Block> blockList,List<Block> finish_distribute_blocklist, List<HomeWork> homeWorkList){
        HomeWork h = null;
        Block b = null;
        int temp = 0;
        for (Iterator homework = homeWorkList.iterator(); homework.hasNext();){
            h = (HomeWork) homework.next();
            int j=0,k=0;
            while (j<=blockList.size()){
                if (j==0) k = temp;
                if (temp>=blockList.size()) temp = 0;
                b = blockList.get(temp);
                if (h.length<=b.length ){
                    Block block1 = new Block(b.InitialAddress,h.length,h.homework_name);
                    finish_distribute_blocklist.add(block1);
                    System.out.println("-----------------------------------------------------------------");
                    System.out.printf("分配作业 %s 成功！\n",h.homework_name);
                    System.out.printf("分配起始位置为：%d   分配长度为：%d\n",b.InitialAddress,h.length);
                    b.InitialAddress = b.InitialAddress+h.length;
                    b.length = b.length-h.length;
                    Block.displayBlock(blockList,finish_distribute_blocklist);
                    temp++;
                    break;
                }
                j++;
                temp ++;
            }
            if(j>blockList.size()){
                System.out.println("-----------------------------------------------------------------");
                System.out.printf("分配作业 %s 失败！\n",h.homework_name);
                temp = k;
            }
        }
    }


}
