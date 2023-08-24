package lab2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsImpl implements Goods,Remote,Serializable {

	String name=null;
	List<String> body;
	int index=0;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;

	}

	@Override
	public String[] getBody() {
		if(body==null) {
			return null;
		}
		//return (String[])body.toArray();
		return body.toArray(new String[0]);
	}

	@Override
	public void setBody(String[] body) {
		this.body=Arrays.asList(body);
		index=0;

	}

	@Override
	public String readLine() {
		if(body==null || body.size()==index) {
			return null;
		}
		return body.get(index++);
	}

	@Override
	public void printLine(String body) {
		if(this.body==null) {
			this.body=new ArrayList<>();
			index=0;
		}
		this.body.add(body);

	}

	@Override
	public int getNumLines() {
		if(body==null) {
			return -1;
		}
		return body.size();
	}

	@Override
	public void save(String name) {
		if(body==null || name==null) {
			return;
		}
		
		try(BufferedWriter buffer=new BufferedWriter(new FileWriter(name))){
			
			for(String s:body) {
				buffer.append(s+"\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void load(String name) {
		
		body=new ArrayList<>();
		index=0;
		try(BufferedReader buffer=new BufferedReader(new FileReader(name))){
			
			String line=null;
			while((line=buffer.readLine())!=null) {
				System.out.println(line+"--");
				body.add(line);
			}
			
			index=0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		List<String> proba=new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			proba.add("milan"+i);
		}
		
		GoodsImpl goods=new GoodsImpl();
		
		String[] niz=proba.toArray(new String[0]);
		goods.setBody(niz);
		goods.save("milan");
		System.out.println(goods.getNumLines());
		goods.load("milan");
		System.out.println(goods.readLine());
		goods.printLine("nikola");
		goods.save("nikola");
	}
	

}
