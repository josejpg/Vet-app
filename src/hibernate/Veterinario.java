package hibernate;

import java.io.*;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Veterinario {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		try {

			// Muestra el listado de opciones
			showOptions();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Inicializa la aplicación y muestra las opciones disponibles
	 * @throws IOException 
	 */
	private static void showOptions() throws IOException {
		
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		String _option = null;
		
		System.out.flush();
		System.out.println( "Veterinario" );
		System.out.println( "----------" );
		System.out.println( "" );
		System.out.println( "1 - Añadir perro" );
		System.out.println( "2 - Añadir gato" );
		System.out.println( "3 - Buscar perro por nombre" );
		System.out.println( "4 - Buscar gato por nombre" );
		System.out.println( "5 - Listar perros" );
		System.out.println( "6 - Listar gatos" );
		System.out.println( "0 - Salir" );
		System.out.println( "" );
		System.out.println( "Elige una opción:" );
		
		// Recuperar la opción escogida
		_option = _reader.readLine();
		System.out.println( _option );
		
		// Realizar la opción seleccionada
		switch (_option ) {
			case "1": // Añadir perro
				addPerro();
				break;

			case "2": // Añadir gato
				addGato();
				break;

			case "3": // Buscar perro por nombre
				getPerroByName();
				break;

			case "4": // Buscar gato por nombre
				getGatoByName();
				break;

			case "5": // Listar perros
				getPerros();
				break;

			case "6": // Listar gatos
				getGatos();
				break;

			case "0": // Salir
				closeApp();
				break;
			default:
				showOptions();
				break;
		}
	}
	
	/**
	 * Finaliza la aplicación
	 */
	private static void closeApp() {
		System.out.flush();
		System.out.println( "El programa se ha finalizado." );
		System.exit(0);
	}
	
	/**
	 * Añade un perro a la DB
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void addPerro() throws IOException {
		PerroId perroId = new PerroId();
		Perro perro = new Perro();
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.flush();
		System.out.println( "- Añadiendo Perro -" );
		
		System.out.print( "Nombre: " );
		perroId.setNombre( _reader.readLine() );
		
		System.out.print( "Edad: " );
		perroId.setEdad( Integer.parseInt( _reader.readLine() ) );

		System.out.print( "Raza: " );
		perroId.setRaza( _reader.readLine() );
		
		System.out.print( "Color: " );
		perroId.setColor( _reader.readLine() );
		
		System.out.print( "Sexo (M/H): " );
		perroId.setSexo( _reader.readLine().toUpperCase().charAt( 0 ) );
		
		System.out.print( "Pelaje: " );
		perroId.setPelaje( _reader.readLine() );
		
		System.out.print( "Raza peligrosa (Si/No): " );
		perroId.setPeligrosa( ( _reader.readLine().toLowerCase().trim().matches( "si|sí" ) ) );
		
		System.out.print( "Observaciones: " );
		perroId.setObservaciones( _reader.readLine() );
		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction trans = session.beginTransaction(); 
		perro.setId( perroId );
		session.save( perro ); 
		trans.commit(); 
		session.close();
		System.out.println( "El perro se ha añadido correctamente." );
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}
	}
	
	/**
	 * Añade un gato a la DB
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void addGato() throws IOException {
		GatoId gato = new GatoId();
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.flush();
		System.out.println( "- Añadiendo Gato -" );
		
		System.out.print( "Nombre: " );
		gato.setNombre( _reader.readLine() );
		
		System.out.print( "Edad: " );
		gato.setEdad( Integer.parseInt( _reader.readLine() ) );

		System.out.print( "Raza: " );
		gato.setRaza( _reader.readLine() );
		
		System.out.print( "Color: " );
		gato.setColor( _reader.readLine() );
		
		System.out.print( "Sexo (M/H): " );
		gato.setSexo( _reader.readLine().toUpperCase().charAt( 0 ) );
		
		System.out.print( "Tiene uñas (Si/No): " );
		gato.setUnyas( _reader.readLine().toLowerCase().trim().matches( "si|sí" ) );
		
		System.out.print( "Observaciones: " );
		gato.setObservaciones( _reader.readLine() );
		
		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction trans = session.beginTransaction(); 
		session.save( new Gato( gato ) ); 
		trans.commit(); 
		session.close();
		System.out.println( "El gato se ha añadido correctamente." );
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}	
	}
	
	/**
	 * Recoge todos los perros de la DB
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void getPerros() throws IOException {
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Perro> query = session.createQuery( "from Perro" );
		List<Perro> listPerros = query.list();
		
		if( listPerros.size() > 0 ) {
			System.out.println( "- Listado de perros -" );
			
			for ( Perro perro: listPerros ){
				System.out.println( "Perro:" );
				System.out.println( "" );
				System.out.println( "Nombre => " + perro.getId().getNombre() );
				System.out.println( "Edad => " + perro.getId().getEdad() );
				System.out.println( "Raza => " + perro.getId().getRaza() );
				System.out.println( "Color => " + perro.getId().getColor() );
				System.out.println( "Sexo => " + perro.getId().getSexo() );
				System.out.println( "Pelaje => " + perro.getId().getPelaje() );
				System.out.println( "Peligrosa => " + ( (perro.getId().isPeligrosa())?"Sí":"No" ) );
				System.out.println( "Observaciones => " + perro.getId().getObservaciones() );
				System.out.println( " ----- " );
			}
			session.close();
		}else {
			System.out.println( "No se han encontrado perros en el sistema." );
		}
		
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}
	}
	
	/**
	 * Recoge todos los perros de la DB que tengan el nombre indicado
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void getPerroByName() throws IOException {
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		String name = null;
		System.out.println( "Buscando perro..." );
		System.out.print( "Nombre: " );
		name = _reader.readLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Perro> query = session.createQuery( "from Perro where nombre LIKE '%" + name + "%'" );
		List<Perro> listPerros = query.list();
		
		if( listPerros.size() > 0 ) {
			System.out.println( "- Listado de perros -" );
			
			for ( Perro perro: listPerros ){
				System.out.println( "Perro:" );
				System.out.println( "" );
				System.out.println( "Nombre => " + perro.getId().getNombre() );
				System.out.println( "Edad => " + perro.getId().getEdad() );
				System.out.println( "Raza => " + perro.getId().getRaza() );
				System.out.println( "Color => " + perro.getId().getColor() );
				System.out.println( "Sexo => " + perro.getId().getSexo() );
				System.out.println( "Pelaje => " + perro.getId().getPelaje() );
				System.out.println( "Peligrosa => " + ( (perro.getId().isPeligrosa())?"Sí":"No" ) );
				System.out.println( "Observaciones => " + perro.getId().getObservaciones() );
				System.out.println( " ----- " );
			}
			
		}else {
			System.out.println( "No se han encontrado perros con el nombre '" + name + "' en el sistema." );
		}
		
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}
	}

	/**
	 * Recoge todos los gatos de la DB
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void getGatos() throws IOException {
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Gato> query = session.createQuery( "from Gato" );
		List<Gato> listGatos = query.list();
		
		if( listGatos.size() > 0 ) {
			System.out.println( "- Listado de gatos -" );
			
			for ( Gato gato: listGatos ){
				System.out.println( "Gato:" );
				System.out.println( "" );
				System.out.println( "Nombre => " + gato.getId().getNombre() );
				System.out.println( "Edad => " + gato.getId().getEdad() );
				System.out.println( "Raza => " + gato.getId().getRaza() );
				System.out.println( "Color => " + gato.getId().getColor() );
				System.out.println( "Sexo => " + gato.getId().getSexo() );
				System.out.println( "Uñas => " + ( (gato.getId().isUnyas())?"Sí":"No" ) );
				System.out.println( "Observaciones => " + gato.getId().getObservaciones() );
				System.out.println( " ----- " );
			}
			
		}else {
			System.out.println( "No se han encontrado gatos en el sistema." );
		}
		
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}
	}
	
	/**
	 * Recoge todos los gatos de la DB que tengan el nombre indicado
	 */
	@SuppressWarnings("deprecation")
	private static void getGatoByName() throws IOException {
		BufferedReader _reader = new BufferedReader( new InputStreamReader( System.in ) );
		String name = null;
		System.out.println( "Buscando gato..." );
		System.out.print( "Nombre: " );
		name = _reader.readLine();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Gato> query = session.createQuery( "from Gato where nombre LIKE '%" + name + "%'" );
		List<Gato> listGatos = query.list();
		
		if( listGatos.size() > 0 ) {
			System.out.println( "- Listado de gatos -" );
			
			for ( Gato gato: listGatos ){
				System.out.println( "Gato:" );
				System.out.println( "" );
				System.out.println( "Nombre => " + gato.getId().getNombre() );
				System.out.println( "Edad => " + gato.getId().getEdad() );
				System.out.println( "Raza => " + gato.getId().getRaza() );
				System.out.println( "Color => " + gato.getId().getColor() );
				System.out.println( "Sexo => " + gato.getId().getSexo() );
				System.out.println( "Uñas => " + ( (gato.getId().isUnyas())?"Sí":"No" ) );
				System.out.println( "Observaciones => " + gato.getId().getObservaciones() );
				System.out.println( " ----- " );
			}
			
		}else {
			System.out.println( "No se han encontrado gatos con el nombre '" + name + "' en el sistema." );
		}
		
		System.out.println( "Presiona cualquier tecla para volver al menú." );
		if( _reader.readLine() != null ) {
			showOptions();
		}
	}
}
