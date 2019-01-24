package testingclient;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class TestingClient {

    public static void main(String[] args) {
        String firstInterface = null;
        Map<String, String> addressByNetwork = new HashMap<>();
        Enumeration<NetworkInterface> networkInterfaces;
        try{
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()){
                NetworkInterface network = networkInterfaces.nextElement();

                byte[] bmac = network.getHardwareAddress();
                if(bmac != null){
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < bmac.length; i++){
                        sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));
                    }
                    if(sb.toString().isEmpty()==false){
                        addressByNetwork.put(network.getName(), sb.toString());
                    }
                    if(sb.toString().isEmpty()==false && firstInterface == null){
                        firstInterface = network.getName();
                    }
                }
            }
        }catch (SocketException e){
            System.out.println(e.getMessage());
        }
        if(firstInterface != null){
            String MACAddress= addressByNetwork.get(firstInterface);
            System.out.println(MACAddress);
        }
    }
}
