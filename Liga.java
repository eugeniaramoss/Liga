import java.util.ArrayList;
import java.util.Scanner;

public class Liga {

	private String nombre;
	private ArrayList<Equipo> todosEquipos = new ArrayList<Equipo>();
	private ArrayList<Jugador> todosJugadores = new ArrayList<Jugador>();

	Scanner lector = new Scanner(System.in);

	public Liga(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Liga() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Equipo> getTodosEquipos() {
		return todosEquipos;
	}

	public void setTodosEquipos(ArrayList<Equipo> todosEquipos) {
		this.todosEquipos = todosEquipos;
	}

	public ArrayList<Jugador> getTodosJugadores() {
		return todosJugadores;
	}

	public void setTodosJugadores(ArrayList<Jugador> todosJugadores) {
		this.todosJugadores = todosJugadores;
	}

	@Override
	public String toString() {
		return "Liga{" + "nombre='" + nombre + '\'' + ", todosEquipos=" + todosEquipos + ", todosJugadores="
				+ todosJugadores + '}';
	}

	// Añadir

	public void addEquipo(String nombre) {
		todosEquipos.add(new Equipo(nombre));
	}

	public void addEquipoGuiado() {
		String nombreEquipo;

		System.out.println("Introduzca el nombre del equipo que quiere añadir: ");
		nombreEquipo = Gestion.sacarYcomprobarString();

		todosEquipos.add(new Equipo(nombreEquipo));
	}

	public void addJugador(Jugador item, int idEquipo) {
		todosEquipos.get(idEquipo).addJugador(item);
	}

	public void addJudador(String nombre, String apellido, String clase) {
		todosJugadores.add(new Jugador(nombre, apellido, clase));
	}

	public void addJugador(String NEquipo, String nombre, String apellido, String clase) {
		int lastJugador;
		int longitud;

		lastJugador = todosJugadores.size() - 1;
		longitud = todosEquipos.size();

		todosJugadores.add(new Jugador(nombre, apellido, clase));
		todosJugadores.get(lastJugador).setEQ(NEquipo);

		for (int j = 0; j < longitud; j++) {
			if (todosEquipos.get(j).getNombre().equals(NEquipo)) {
				this.addJugador(todosJugadores.get(lastJugador), j);
			}
		}
	}

	public void addJugadorGuiado() {
		boolean val;
		boolean val2;
		boolean YoN;
		int i;
		int num;
		String nombrejugador;
		String apellidojugador;
		String clasejugador;
		int longitud;
		String copia;
		int lastJugador;

		lastJugador = todosJugadores.size();

		System.out.println("Introduzca el nombre del jugador (no se admiten ni espacios ni numeros)");
		nombrejugador = Gestion.sacarYcomprobarString();

		do {
			val = true;
			System.out.println("Introduzca el apellido de " + nombrejugador + " (no se admiten numeros)");
			apellidojugador = lector.nextLine();

			if (apellidojugador == null || apellidojugador.equals("")) {
				val = false;
				System.out.println("Introduzca algun contenido.");

			} else {
				i = 0;
				longitud = apellidojugador.length();

				do {
					val2 = false;
					copia = apellidojugador.charAt(i) + "";
					try {
						Double.parseDouble(copia);
					} catch (NumberFormatException nfe) {
						val2 = true;
					}

					if (!val2) {
						System.out.println("No se permiten numeros en el apellido");
						val = false;
						val2 = false;
					}
					i++;
				} while (val2 && i < longitud);
			}
		} while (!val);

		System.out.println("Introduzca la clase de " + nombrejugador);
		clasejugador = lector.nextLine();

		todosJugadores.add(new Jugador(nombrejugador, apellidojugador, clasejugador));

		if (todosEquipos.size() > 0) {
			System.out.println("¿Desea añadir el jugador creado a un equipo?");
			YoN = Gestion.sacarYcomprobarYoN();

			if (YoN) {

				this.mostrarEquiposYPlantilla();
				System.out.println("Elija id el equipo al que pertenece el jugador");
				longitud = todosEquipos.size();
				num = Gestion.sacarYcomprobarNumero(0, longitud);

				todosJugadores.get(lastJugador).setEQ(todosEquipos.get(num).getNombre());
				todosEquipos.get(0).addJugador(todosJugadores.get(lastJugador));
			} else {
				System.out.println("Si desea insertarlo a un equipo, debe entrar a Equipo ---> Añadir jugador");
			}
		} else {
			System.out.println(
					"No hay Equipos creados, si desea añadir este jugador a un equipo, cree el equipo y transfiera el jugador a su equipo preferido");
		}
	}

	public void ficharJugador() {
		int idJugador;
		int longitudEQ = todosEquipos.size();
		int longitudJuga = todosJugadores.size();
		int idEquipo = 0;

		this.mostrarEquipos();

		if (longitudEQ > 0) {

			System.out.println("Seleccione un equipo");
			idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);

			if (longitudJuga > 0) {
				this.mostrarJugadores();

				System.out.println("Seleccione el jugador que quiere añadir a ese equipo");
				idJugador = Gestion.sacarYcomprobarNumero(0, longitudJuga);

				if (todosJugadores.get(idJugador).getEQ() == null) {
					todosJugadores.get(idJugador).setEQ(todosEquipos.get(idEquipo).getNombre());
					todosEquipos.get(idEquipo).getplantilla().add(todosJugadores.get(idJugador));
				} else {
					System.out.println("Este jugador ya pertenece a un equipo");
				}
			} else {
				System.out.println("No hay jugadores creados.");
			}
		}
	}

	// Modificar

	public void modEquipoGuiado() {
		int num;
		String nombreEquipo;
		int longitudEQ = todosEquipos.size();
		int longitudjug;

		this.mostrarEquipos();
		if (longitudEQ > 0) {

			System.out.println("Elija id del equipo que quiere modificar");
			num = Gestion.sacarYcomprobarNumero(0, longitudEQ);

			System.out.println("Introduzca el nuevo nombre del equipo: ");
			nombreEquipo = Gestion.sacarYcomprobarString();

			longitudjug = todosJugadores.size();
			String nAnterior = todosEquipos.get(num).getNombre();
			for (int i = 0; i < longitudjug; i++) {
				if (todosJugadores.get(i).getEQ().equals(nAnterior)) {
					todosJugadores.get(i).setEQ(nombreEquipo);
				}
			}
			todosEquipos.get(num).setNombre(nombreEquipo);
			System.out.println("Nombre cambiado con Exito");
		}
	}

	public void modJugador() {
		int selector;
		int longitudJuga = todosJugadores.size();
		boolean veri = true;
		Jugador jSeleccionado;
		Jugador jPlantilla;
		String NjGeneral;
		String EQGeneral;
		String clasejugador;

		if (longitudJuga > 0) {

			jSeleccionado = this.seleccionarJugador();

			NjGeneral = jSeleccionado.getNombre();
			EQGeneral = jSeleccionado.getEQ();
			jPlantilla = this.sacarJugadorItem(NjGeneral, EQGeneral);

			do {
				System.out.println("Seleccione lo que quiere hacer al jugador: " + jSeleccionado.getNombre());
				System.out.println("1 - Modificar todo un jugador");
				System.out.println("2 - Modificar el Nombre");
				System.out.println("3 - Modificar el Apellido");
				System.out.println("4 - Modificar la clase");
				System.out.println("5 - Salir\n");

				selector = Gestion.sacarYcomprobarNumero(1, 5);

				switch (selector) {
				case 1:
					jSeleccionado.setNombre(this.sacarYcomprobarDatosJugador("Nombre", jSeleccionado));
					jSeleccionado.setApellido(this.sacarYcomprobarDatosJugador("Apellido", jSeleccionado));
					System.out.println("Introduzca la nueva clase de " + jSeleccionado.getNombre());
					clasejugador = lector.nextLine();
					jSeleccionado.setClase(clasejugador);
					break;
				case 2:
					jSeleccionado.setNombre(this.sacarYcomprobarDatosJugador("Nombre", jSeleccionado));
					break;
				case 3:
					jSeleccionado.setApellido(this.sacarYcomprobarDatosJugador("Apellido", jSeleccionado));
					break;
				case 4:
					System.out.println("Introduzca la nueva clase de " + jSeleccionado.getNombre());
					clasejugador = lector.nextLine();
					jSeleccionado.setClase(clasejugador);
					break;
				case 5:
					veri = false;
					break;
				}
				if (jPlantilla != null) {
					jPlantilla.setNombre(jSeleccionado.getNombre());
					jPlantilla.setApellido(jSeleccionado.getApellido());
					jPlantilla.setClase(jSeleccionado.getClase());
				}
			} while (veri);
		} else {
			System.out.println("No se pueden modificar jugadores si no hay jugadores.");
		}
	}

	public void cambioDeEquipo() {
		int idJugador;
		int idEquipoTransfer;
		int equipoOG;
		int longitudEQ = todosEquipos.size();
		int longitudPlantilla;
		int longitudJuga = todosJugadores.size() - 1;
		int idEquipo = 0;
		Jugador nJugador;

		if (longitudEQ > 1) {

			this.mostrarEquiposYPlantilla();
			System.out.println("Introduzca el id del Equipo");
			equipoOG = Gestion.sacarYcomprobarNumero(0, todosEquipos.size());
			longitudPlantilla = todosEquipos.get(equipoOG).getplantilla().size();

			if (longitudPlantilla > 0) {

				System.out.println("Introduzca el id del jugador");
				idJugador = Gestion.sacarYcomprobarNumero(0, longitudPlantilla);

				this.mostrarEquipos();
				longitudPlantilla = todosEquipos.size();
				System.out.println("Introduzca el id del Equipo al que quiere ser transferido");
				idEquipoTransfer = Gestion.sacarYcomprobarNumero(0, longitudPlantilla);

				System.out.println("El jugador: " + todosEquipos.get(equipoOG).getplantilla().get(idEquipo).getNombre()
						+ " a sido transferido al Equipo: " + todosEquipos.get(idEquipoTransfer).getNombre());
				todosEquipos.get(idEquipoTransfer).addJugador(todosEquipos.get(equipoOG).getplantilla().get(idJugador));
				nJugador = todosEquipos.get(equipoOG).getplantilla().get(idJugador);
				for (int i = 0; i <= longitudJuga; i++) {
					if (todosJugadores.get(i).equals(nJugador)) {
						todosJugadores.get(i).setEQ(todosEquipos.get(idEquipoTransfer).getNombre());
					}
				}
				todosEquipos.get(equipoOG).getplantilla().remove(idJugador);
			} else {
				System.out.println("Este Equipo no tiene Jugadores, operacion cancelada.");
			}
		} else {
			System.out.println("No hay suficientes Equipos para hacer una transferencia.");
		}
	}

	// Mostrar

	public void mostrarEquipos() {
		int longitud;
		longitud = todosEquipos.size();

		if (longitud > 0) {
			for (int j = 0; j < longitud; j++) {
				System.out.println(j + " --> " + todosEquipos.get(j).getNombre());
				System.out.println("\n");
			}
		} else {
			System.out.println("No hay Equipos creados.");
		}
	}

	public void mostrarEquiposYPlantilla() {
		int longitudEQ = todosEquipos.size();

		if (longitudEQ > 0) {
			for (int j = 0; j < longitudEQ; j++) {
				System.out.println("");
				System.out.println(j + " --> " + todosEquipos.get(j).getNombre());
				this.mostrarPlantilladeEquipo(j);
				System.out.println("\n");
			}
		} else {
			System.out.println("No hay Equipos creados.");
		}
	}

	// Jugadores

	public void mostrarJugadores() {
		int longitud;
		longitud = todosJugadores.size();

		if (longitud > 0) {
			for (int j = 0; j < longitud; j++) {
				System.out.println(j + " --> " + todosJugadores.get(j).toString());
			}
		} else {
			System.out.println("No hay Jugadores creados.");
		}
	}

	public void mostrarPlantilladeEquipo(int idEquipo) {
		int longitud = todosEquipos.get(idEquipo).getplantilla().size();

		if (longitud > 0) {
			for (int j = 0; j < longitud; j++) {
				System.out.println(j + " ------> " + todosEquipos.get(idEquipo).getplantilla().get(j));
			}
		} else {
			System.out.println("Este equipo no tiene jugadores");
		}
	}

	// Eliminar

	public void rmEquipo() {

		int idEquipo;
		int longitudEQ = todosEquipos.size();
		int longitudJuga;
		String nombreEQ;

		this.mostrarEquiposYPlantilla();
		if (longitudEQ > 0) {

			System.out.println("Introduzca el id del Equipo");
			idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);

			nombreEQ = todosEquipos.get(idEquipo).getNombre();
			System.out.println("Equipo " + nombreEQ + " ha sido eliminad@");
			longitudJuga = todosJugadores.size() - 1;

			if (longitudJuga > 0) {
				for (int i = 0; i <= longitudJuga; i++) {

					try {
						if (todosJugadores.get(i).getEQ().equals(nombreEQ)) {
							todosJugadores.get(i).setEQ(null);
						}
					} catch (NullPointerException NPE) {
					}
				}

				todosEquipos.get(idEquipo).rmPlantilla();
			}

			todosEquipos.remove(idEquipo);
			this.mostrarEquiposYPlantilla();
		}

	}

	// Jugador

	public void rmJugador() {
		int idJugador;
		int longitudEQ = todosEquipos.size() - 1;
		int longitudJuga = todosJugadores.size();
		int longitudPlant;
		String nJugador;
		String nombreEQ;

		this.mostrarJugadores();
		idJugador = Gestion.sacarYcomprobarNumero(0, longitudJuga);

		nJugador = todosJugadores.get(idJugador).getNombre();
		nombreEQ = todosJugadores.get(idJugador).getEQ();
		todosJugadores.remove(idJugador);

		if (nombreEQ != null) {
			for (int i = 0; i < longitudEQ; i++) {

				if (todosEquipos.get(i).getNombre().equals(nombreEQ)) {
					longitudPlant = todosEquipos.get(i).getplantilla().size();

					if (longitudPlant > 0) {
						for (int j = 0; j < longitudPlant; j++) {

							if (todosEquipos.get(i).getplantilla().get(j).getNombre().equals(nJugador)) {
								todosEquipos.get(i).getplantilla().remove(j);
							}
						}
					}
				}
			}
		}
	}

	public void rmJugadorDeEquipo() {
		int longitudEQ = todosEquipos.size();
		int longitudPlantilla;
		int longitudJuga;
		int idEquipo;
		Jugador nJugador;

		this.mostrarEquiposYPlantilla();
		if (longitudEQ > 0) {

			System.out.println("Introduzca el id del Equipo");
			idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);
			longitudPlantilla = todosEquipos.get(idEquipo).getplantilla().size();

			if (longitudPlantilla > 0) {
				longitudJuga = todosJugadores.size() - 1;

				nJugador = todosEquipos.get(idEquipo).rmJugadordePlantilla();
				for (int i = 0; i <= longitudJuga; i++) {
					if (todosJugadores.get(i).equals(nJugador)) {
						todosJugadores.get(i).setEQ(null);
					}
				}
			} else {
				System.out.println("Este Equipo no tiene jugadores a eliminar");
			}
		}
	}

	// Otros

	private String sacarYcomprobarDatosJugador(String dato, Jugador item) { // Das lo que quieres cambiar y el objeto
																			// jugador que quieres cambiar
		String datojugador;
		boolean val;
		boolean val2;
		do {
			val = true;
			System.out.println("Introduzca el " + dato + " del jugador " + item.getNombre()
					+ "(no se admiten ni espacios ni numeros)");
			datojugador = lector.nextLine();
			if (datojugador == null || datojugador.equals("")) { // Si esta vacio el string, te hace repetir el nombre
				val = false;
				System.out.println("Introduzca algun contenido.");
			} else {
				val2 = false;

				if (datojugador.matches("[a-zA-Z]+")) {
					val2 = true;
				}
				if (!val2) {
					System.out.println("Solo se permiten letras");
					val = false;
				}
			}
		} while (!val);

		return datojugador;
	}

	private Jugador seleccionarJugador() {
		int idjugador;
		int longitud = todosJugadores.size() - 1;

		this.mostrarJugadores();

		System.out.println("Seleccione el jugador");
		idjugador = Gestion.sacarYcomprobarNumero(0, longitud);

		return todosJugadores.get(idjugador);
	}

	private Jugador seleccionarJugadordePlantilla() {
		int idjugador;
		int longitud = todosEquipos.size() - 1;
		int longitud2;
		int idEquipo;

		this.mostrarEquiposYPlantilla();
		System.out.println("Seleccione un id de Equipo");
		idEquipo = Gestion.sacarYcomprobarNumero(0, longitud);

		this.mostrarPlantilladeEquipo(idEquipo);
		longitud2 = todosEquipos.get(idEquipo).getplantilla().size() - 1;
		System.out.println("Seleccione un id de jJugador");
		idjugador = Gestion.sacarYcomprobarNumero(0, longitud2);

		return todosEquipos.get(idEquipo).getplantilla().get(idjugador);
	}

	private Jugador sacarJugadorItem(String nombreEQ, String nombreJugador) {
		boolean valEQ;
		boolean valJuga;
		int i = 0;
		int j = 0;
		int longitudEQ = todosEquipos.size();
		int longitudJuga;
		int idEquipo = 0;
		Jugador jSeleccionado = null;

		if (longitudEQ > 0) {
			longitudEQ--;
			do {
				valEQ = true;
				if (todosEquipos.get(i).getNombre().equals(nombreEQ)) {
					idEquipo = i;
					valEQ = false;
				}
				i++;
			} while (valEQ && i < longitudEQ);

			if (!valEQ) {
				longitudJuga = todosEquipos.get(idEquipo).getplantilla().size();
				do {
					valJuga = true;
					if (todosEquipos.get(idEquipo).getplantilla().get(j).getNombre().equals(nombreJugador)) {
						jSeleccionado = todosEquipos.get(idEquipo).getplantilla().get(j);
					}
				} while (valJuga && j < longitudJuga);
			}
		}
		return jSeleccionado;
	}
}
