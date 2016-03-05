package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import negocio.GestionarArchivo;

/**
 * Servlet implementation class SeguimientoControlador
 */
@WebServlet("/SeguimientoControlador")
public class SeguimientoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession recibiendoUsuarioActual;
	private Usuario usuarioActual, usuarioActualizado;
	private GestionarArchivo archivoActualizar;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguimientoControlador() {
        super();
        // TODO Auto-generated constructor stub
        usuarioActual = new Usuario();
        usuarioActualizado = new Usuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		recibiendoUsuarioActual = request.getSession();
		usuarioActual = (Usuario)recibiendoUsuarioActual.getAttribute("USUARIOACTUAL");
		archivoActualizar = new GestionarArchivo(request.getServletContext().getRealPath("/")+"/archivos");
		
//		Obtencion de datos a actualizar
		String nombreActualizado = request.getParameter("nombreActualizado");
		String apellidoPaternoActualizado = request.getParameter("apellidoPaternoActualizado");
		String apellidoMaternoActualizado = request.getParameter("apellidoMaternoActualizado");
		String correoActualizado = request.getParameter("correoActualizado");
		String contrasenaActualizado = request.getParameter("contrasenaActualizado");
		
		System.out.println("nombre actualizado: "+ nombreActualizado);
		System.out.println("apellidoP actualizado: "+ apellidoPaternoActualizado);
		System.out.println("apellidoM actualizado: "+ apellidoMaternoActualizado);
		System.out.println("correo actualizado: "+ correoActualizado);
		System.out.println("contrasena actualizado: "+ contrasenaActualizado);
		
//		Envio de datos a actualizar
		usuarioActualizado.setNombres(nombreActualizado);
		usuarioActualizado.setApellidoPaterno(apellidoPaternoActualizado);
		usuarioActualizado.setApellidoMaterno(apellidoMaternoActualizado);
		usuarioActualizado.setCorreo(correoActualizado);
		usuarioActualizado.setContrasena(contrasenaActualizado);
		
		boolean actualizacion = archivoActualizar.actualizarUsuario(usuarioActual, usuarioActualizado);
		
		if(actualizacion){
			try{
				PrintWriter mostrarDatos = response.getWriter();
				mostrarDatos.println("<!DOCTYPE html><html>");
				mostrarDatos.println("<head><h1>Actualizacion de datos </h1></head>");
				mostrarDatos.println("<body>");
				mostrarDatos.println("<header><h1>Actualizacion exitosa!!</h1></header>");
				mostrarDatos.println("<div id='contenedorPrincipal'>");
				mostrarDatos.println("<section id='seccionMostrarDatos'>");
				mostrarDatos.println("<form id='formularioActualizacionMostrar' action='Login.html' >");
				mostrarDatos.println("<label name='nombreActualizadoMostrar'>"+nombreActualizado +"</label><br>");
				mostrarDatos.println("<label name='apellidoPaternoActualizadoMostrar'>"+apellidoPaternoActualizado +"</label><br>");
				mostrarDatos.println("<label name='apellidoMaternoActualizadoMostrar'>"+apellidoMaternoActualizado +"</label><br>");
				mostrarDatos.println("<label name='correoActualizadoMostrar'>"+correoActualizado +"</label><br>");
				mostrarDatos.println("<label name='contrasenaActualizadoMostrar'>"+contrasenaActualizado +"</label><br>");
				mostrarDatos.println("<button type='submit'>Inicio</button>");
				mostrarDatos.println("</form>");
				mostrarDatos.println("</section>");
				mostrarDatos.println("</div>");
				mostrarDatos.println("</body>");
				mostrarDatos.println("</html>");
				mostrarDatos.close();
			}catch(Exception e){
				
			}
		}else{
			response.sendRedirect("actualizacionUsuarioFallida.html");		
		}
	}

}
