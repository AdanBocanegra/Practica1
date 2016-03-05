package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.Usuario;

public class GestionarArchivo {
	private File rutaDelArchivo;
	private Usuario unUsuario;
	private String ruta;
	
	public GestionarArchivo(){
		rutaDelArchivo = null;
		unUsuario = new Usuario();
	}
	
	public GestionarArchivo(String ruta) {
		unUsuario = new Usuario();
		this.ruta=ruta;
		try {
			//Creacion o apertura del archivo
			rutaDelArchivo = new File(ruta,"prueba.txt");
			System.out.println(rutaDelArchivo.getPath());

		} catch (NullPointerException e) {
			System.out.println("Error al crear el archivo" + e);
		}
	}

	public boolean crearUsuario(Usuario usuario) {
		try {
			FileWriter agregarUsuario = new FileWriter(rutaDelArchivo, true);
			agregarUsuario.write(usuario.getNombres() + "|" + usuario.getApellidoPaterno() + "|"
					+ usuario.getApellidoMaterno() + "|" + usuario.getCorreo() + "|" + usuario.getContrasena() + "\n");
			agregarUsuario.close();
			return true;
			
		} catch (IOException e) {
			System.out.println("Error al agregar un usuario" + e);
			return false;
		}
	
	}

	public void actualizarUsuario(Usuario usuario) {

	}

	// login
	public Usuario buscarUsuario(String usuario) {
		String linea=" | | | | ";
		String []lineaDividida=null;
		try {
			BufferedReader bufferLinea = new BufferedReader(new FileReader(rutaDelArchivo));

			while (linea!= null) {
				lineaDividida = linea.split("\\|");				
				if(lineaDividida.length==0){
					unUsuario=null;
					bufferLinea.close();
					return unUsuario;
				}else if(lineaDividida[3].equals(usuario)){
					System.out.println("Usuario: "+usuario+" encontrado"+" Longitud de lineaDividida: "+lineaDividida.length);
					unUsuario.setNombres(lineaDividida[0]);
					unUsuario.setApellidoPaterno(lineaDividida[1]);
					unUsuario.setApellidoMaterno(lineaDividida[2]);
					unUsuario.setCorreo(lineaDividida[3]);
					unUsuario.setContrasena(lineaDividida[4]);
					bufferLinea.close();
					return unUsuario;
				}
				linea=bufferLinea.readLine();
			}
			bufferLinea.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo" + e);

		}

		return unUsuario;
	}
	
	public void cerarArchivo(){
		
	}
	
	public boolean actualizarUsuario(Usuario usuarioActual , Usuario usuarioActualizado){
		String linea,modificacion;
		String []lineaDividida=null;
		String archivoViejo ="prueba.txt";
		String archivoNuevo ="nuevaPrueba.txt";
		
		BufferedReader bufferLinea = null;
		BufferedWriter bufferArchivo = null;
		
		try{
			bufferLinea =new BufferedReader(new FileReader(ruta+"/"+archivoViejo));
			bufferArchivo =new BufferedWriter(new FileWriter(ruta+"/"+archivoNuevo,true));

			while ((linea=bufferLinea.readLine())!= null) {
				System.out.println("linea del archivo " + linea.length());
				lineaDividida = linea.split("\\|");
				if(!lineaDividida[3].equals(usuarioActual.getCorreo())){
					bufferArchivo.write(linea+"\n");
				}
				else if(lineaDividida[3].equals(usuarioActual.getCorreo())){
					modificacion = usuarioActualizado.getNombres()+"|"+usuarioActualizado.getApellidoPaterno()+"|"+usuarioActualizado.getApellidoMaterno()+"|"+usuarioActualizado.getCorreo()+"|"+usuarioActualizado.getContrasena();
					linea = linea.replace(linea,modificacion);
					bufferArchivo.write(linea+"\n");
				}
			}
		}catch(IOException e){
			return false;
		}finally{
			try{
				if(bufferLinea!=null){
					bufferLinea.close();
				}
			}catch(IOException e){
				
			}
			try{
				if(bufferArchivo!=null){
					bufferArchivo.close();
				}
			}catch(IOException e){
				
			}
		}
		File viejoArchivo = new File(ruta+"/"+archivoViejo);
		viejoArchivo.delete();
		File nuevoArchivo = new File(ruta+"/"+archivoNuevo);
		nuevoArchivo.renameTo(viejoArchivo);
		return true;
	}
}
