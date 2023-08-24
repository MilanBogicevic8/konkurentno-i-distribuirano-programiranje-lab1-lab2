package rw;

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

public class BookImpl implements Book,Remote,Serializable {

	String name;
	List<String> lista=null;
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
		if(lista==null) {
			return null;
		}
		return lista.toArray(new String[0]);
	}

	@Override
	public void setBody(String[] body) {
		lista=Arrays.asList(body);

	}

	@Override
	public String readLine() {
		if(lista==null) {
			return null;
		}
		return lista.get(index++);
	}

	@Override
	public void printLine(String body) {
		if(lista==null) {
			lista=new ArrayList<>();
		}
		lista.add(body);
	}

	@Override
	public int getNumLines() {
		if(lista==null) {return 0;}
		else {return lista.size();}
	}

	@Override
	public void save(String name) {
		
		try(BufferedWriter buffer=new BufferedWriter(new FileWriter(name))){
			for(String s:lista) {
				buffer.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void load(String name) {
		lista=new ArrayList<>();
		index=0;
		try(BufferedReader buffer=new BufferedReader(new FileReader(name))){
			String l=null;
			
			while((l=buffer.readLine())!=null) {
				lista.add(l);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
