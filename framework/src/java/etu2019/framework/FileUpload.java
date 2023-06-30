/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2019.framework;

/**
 *
 * @author koloina
 */
public class FileUpload {
        String name;
	String path;
	byte[] bytes;

	public FileUpload(){

	}

	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return this.name;
	}

	public void setpath( String path ){
		this.path = path;
	}

	public String getpath(){
		return this.path;
	}

	public void setbytes( byte[] bytes ){
		this.bytes = bytes;
	}
	public byte[] getbytes(){
		return this.bytes;
	}

	public String tostring(){
		return this.getname() + " [ " + this.getbytes().length + " ]";
	}
}
