package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Agenda implements Serializable {

	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Grupo[] listaGrupos;
	private Reunion[] listaReuniones;
	private int i;


	public Agenda(String nombre, int numeroContactos,int numeroGrupos,int numeroReuniones) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[numeroContactos];
		this.listaGrupos = new Grupo[numeroGrupos];
		this.listaReuniones = new Reunion[numeroReuniones];
	}


	public Agenda() {
		super();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Contacto[] getListaContactos() {
		return listaContactos;
	}


	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}


	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}


	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}


	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}


	@Override
	public String toString() {
		return "Agenda [nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	/**
	 *
	 * @param newContacto
	 * @throws ContactoException
	 */

	public void aniadirContacto(Contacto newContacto) throws ContactoException{

		Contacto contacto = buscarContacto(newContacto);
		int posDisponible = 0;

		if(contacto != null){
			throw new ContactoException("El contacto ya existe");
		}
		posDisponible = obtenerPosicion();

		if(posDisponible == -1){
			throw new ContactoException("Agenda llena");
		}
		listaContactos[posDisponible] = newContacto;

	}
/**
 * metodo que obtiene la posicion de
 * @return
 */

	private int obtenerPosicion() {
		for (int i=0; i < listaContactos.length;i++);
		if (listaContactos[i]==null){
			return i;
		}
		return -1;
	}

	/**
	 *
	 * @param newContacto
	 * @return
	 */

	private Contacto buscarContacto(Contacto newContacto) {

		List<Contacto> asList = Arrays.asList(listaContactos);

		Optional<Contacto> findFirst = asList.stream().filter(c -> c.equals(newContacto)).findFirst();

		return findFirst.get();
	}
	/**
	 * metodo que lista los contactos
	 */

	public void listarContactos() {
	    for (Contacto contacto : listaContactos) {
	        if (contacto != null) {
	            System.out.println(contacto.toString());
	        }
	    }
	}


	/**
	 * metodo que devuekve si la lista esta llena
	 * @return
	 */

	public boolean agendaLlena() {
	    for (Contacto contacto : listaContactos) {
	        if (contacto == null) {
	            return false;
	        }
	    }
	    return true;
	}
/**
 * metodo que muestra los hucos libres de la agenda
 * @return
 */

	public int huecosLibres() {
	    int contador = 0;
	    for (Contacto contacto : listaContactos) {
	        if (contacto == null) {
	            contador++;
	        }
	    }
	    return contador;
	}

	/**
	 * metodo que elimina el contacto
	 * @param contacto
	 * @throws ContactoException
	 */
	public void eliminarContacto(Contacto contacto) throws ContactoException {
	    if (!existeContacto(contacto)) {
	        throw new ContactoException("El contacto no existe");
	    }
	    for (int i = 0; i < listaContactos.length; i++) {
	        if (listaContactos[i] != null && listaContactos[i].equals(contacto)) {
	            listaContactos[i] = null;
	            System.out.println("El contacto ha sido eliminado");
	            return;
	        }
	    }
	    throw new ContactoException("El contacto no pudo ser eliminado");
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public boolean existeContacto(Contacto contacto) {
	    return Arrays.asList(listaContactos).contains(contacto);
	}



}
