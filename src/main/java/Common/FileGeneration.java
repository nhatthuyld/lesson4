package Common;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileGeneration {


    public ArrayList<HashMap<String, String>> readCsvFile(String path) {

        String[] headers;
        String line;
        ArrayList<HashMap<String, String>> listData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            headers = br.readLine().split(",");
            while ((line = br.readLine()) !=null){
                HashMap<String, String> data = new HashMap<>();
                String[] row = line.split(",");
                for (int i = 0; i < headers.length; i++) {
                    data.put(headers[i], row[i]);
                }
                listData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listData;
    }

    @DataProvider(name = "csvDataProvider")
    public Object[][] getData() throws Exception {
        List<HashMap<String, String>> dataList = readCsvFile( "src//Data//userInfo.csv");
        Object[][] result = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            result[i][0] = dataList.get(i);
        }
        return result;
    }




}
