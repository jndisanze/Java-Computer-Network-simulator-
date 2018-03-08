/*******************************************************************************
 * Copyright (c) 2017 Ndisanze Jean P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Ndisanze Jean P - initial API and implementation
 ******************************************************************************/
package reso.examples.fifo_router;
/**
class for reading and writing
in a file
@author Ndisanze Jean-paul
*/
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
public class TraitementFichier{
	/**
	@param la localisation d'un fichier
	@param ArrayList un tableau
	@return rien ecrit les elements du tableau dans un fichier
	 */
	public static void writePoint(String filename,ArrayList<String> res){
		try{
			BufferedWriter wt=new BufferedWriter(new FileWriter(filename));
			for(int i = 0;i< res.size();i++){	
				String e=""+res.get(i);
				wt.write(e,0,e.length());
				wt.newLine();
			}
			wt.close();
		}
		catch(Exception ex){
		System.out.println(filename+ex.getMessage()+ex.getCause());
		throw new IllegalArgumentException("false filename");
		}
	}
	/**	
	@param la localisation d'un fichier
	@return ArrayList un tableau contenant les lignes du fichier
	 */
	public static ArrayList<String> getDonnes(String filename){
		ArrayList<String> data=new ArrayList<String>();
		try{
			BufferedReader read=new BufferedReader(new FileReader(filename));
			String line=read.readLine();
				while(line!=null){
					data.add(line);
					line=read.readLine();
				}
				read.close();
		}
		catch(Exception ex){
			System.out.println(filename+ex.getMessage()+ex.getCause());
			throw new IllegalArgumentException("false filename");
		}
		return data;
	}



	public static void main(String[] args) {
		ArrayList<String> st = new ArrayList<String>();
		for(int i = 0 ; i< 1000;i++){
			st.add("message :" + i);
		}
		TraitementFichier.writePoint(new Date().toString()+".txt", st);
	}

}
