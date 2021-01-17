/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author yhx
 */
public class DataIndexParse {
   public DataIndexParse(){
       
   } 
   
   public String getTestDataIndex(DataKeyInfo key, int indexClass){
       DataInfoGet dataInfo = new DataInfoGet();
       
       switch(indexClass){
           case 1: //VariantNameIndex
           {
               List<VariantName> vName = dataInfo.getVariantName(key);
               List<String> outVName = new ArrayList<>();
               
               for (int i = 0; i < vName.size(); i ++){
                   outVName.add(vName.get(i).getName());
               }
               //System.out.println(key);
               //System.out.println(outVName);
               Gson gs = new Gson();
               return gs.toJson(outVName);
           }
           case 2: //VersionIndex
           {
               List<DataVersion> ver = dataInfo.getDataVersion(key);
               List<String> outVer = new ArrayList<>();
               
               for (int i = 0; i < ver.size(); i ++){
                   outVer.add(ver.get(i).getVersion());
               }
               
               Gson gs = new Gson();
               return gs.toJson(outVer);
           }
           case 3: //TestDataIndex
           {
                List<DataIndex> dIndex =  dataInfo.getDataIndex(key);
                List<Integer> outIndex = new ArrayList<>();

                for (int i = 0; i < dIndex.size(); i ++){
                     outIndex.add(dIndex.get(i).getIndex());
                }
                
                Gson gs = new Gson();
                return gs.toJson(outIndex);
           }
           default:
               return "NULL Index";
       }
   }
}
