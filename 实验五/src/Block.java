import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Block {
    public int InitialAddress;
    public int length;
    public String status;
    public Block(){

    }

    public Block(int InitialAddress, int length, String status) {
        this.InitialAddress = InitialAddress;
        this.length = length;
        this.status = status;
    }


    /**
     * 分区表分配情况显示
     * @param blockList  空闲分区
     * @param finish_distribute_blocklist  已经分配的分区
     */
    public static void displayBlock(List<Block> blockList ,List<Block> finish_distribute_blocklist){
        Block block = null;
        int i=0;
        System.out.println("未分配表和已分配表分配情况为：");
        System.out.println("*******************************************************************************");
        System.out.println("未分配表如下： 地址、长度/kB  ");
        System.out.println("-----------------------------------------------------------------");
        for (Iterator it = blockList.iterator(); it.hasNext();){
            block = (Block) it.next();
            System.out.printf("|    分配区块：%d      起始地址：%d      长度：%d      状态：%s    \n",i,block.InitialAddress,block.length,block.status);
            System.out.println("-----------------------------------------------------------------");
            i++;
        }

        System.out.println("已分配表如下： 地址、长度/kB  ");
        for (Iterator it = finish_distribute_blocklist.iterator(); it.hasNext();){
            block = (Block) it.next();
            System.out.printf("|    分配区块：%d      起始地址：%d      长度：%d      状态：%s    \n",i,block.InitialAddress,block.length,block.status);
            System.out.println("-----------------------------------------------------------------");
            i++;
        }
        System.out.println("********************************************************************************");
    }

}
