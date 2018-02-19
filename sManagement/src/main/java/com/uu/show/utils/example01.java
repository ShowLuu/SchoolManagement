package com.uu.show.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class example01 {
	
	public static void main(String[] args) {
		try {
			InetAddress myip=InetAddress.getLocalHost();
			Socket socket=new Socket(myip,23);
			BufferedReader bread=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pwrite=new PrintWriter(socket.getOutputStream());
			BufferedReader res=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str="";
			str=bread.readLine();
			while(!str.equals("bye")){
				pwrite.println(str);
				pwrite.flush();
				System.out.println("Client: "+str);
				System.out.println("Server: "+str);
				str=bread.readLine();
			}
			res.close();
			pwrite.close();
			bread.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
