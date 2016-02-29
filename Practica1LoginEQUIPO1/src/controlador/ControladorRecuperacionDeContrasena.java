package controlador;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Correo;
import negocio.GestionarArchivo;

/**
 * Servlet implementation class ControladorRecuperacionDeContrasena
 */
@WebServlet("/recuperar")
public class ControladorRecuperacionDeContrasena extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRecuperacionDeContrasena() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		//doGet(request, response);
		String ruta = request.getServletContext().getRealPath("/");
		GestionarArchivo archivoregistro = new GestionarArchivo(ruta+"/archivos");
		String correo = request.getParameter("correo");
		Correo recuperacion = new Correo();
		recuperacion.setCorreo(archivoregistro.buscarUsuario(correo));
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}


}
