import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {

	private Liga LigaSantander = new Liga();

	public void menuInicial() {
		boolean val = true;
		boolean valMenuIni;
		boolean valEliminar;
		int menuGestionlistEquipos;
		int menuGestionJugadores;
		int selectorPrincipal;

		System.out.println("Le damos la bienvenida al programa de creacion de liga");

		do {
			System.out.println("Elija el menu al que quiere acceder:\n");
			System.out.println("1 - Gestion de Equipos");
			System.out.println("2 - Gestion de Jugadores");
			System.out.println("3 - Generar Liga");
			System.out.println("4 - Salir del programa");
			selectorPrincipal = sacarYcomprobarNumero(1, 4);

			switch (selectorPrincipal) {
			case 1:
				valMenuIni = true;

				do {
					System.out.println("Elija una opcion\n");
					System.out.println("0 - Listar Equipos");
					System.out.println("1 - Crear Equipo");
					System.out.println("2 - Modificar Equipo");
					System.out.println("3 - Fichar Jugador");
					System.out.println("4 - Despedir Jugador");
					System.out.println("5 - Eliminar Equipo");
					System.out.println("6 - Insertar Equipos (añade equipos para hacer pruebas)");
					System.out.println("7 - Salir a la selección de menús");
					menuGestionlistEquipos = sacarYcomprobarNumero(0, 7);
					System.out.println("\n");

					switch (menuGestionlistEquipos) {
					case 0:
						LigaSantander.mostrarEquiposYPlantilla();
						System.out.println("\n");
						break;
					case 1:
						LigaSantander.addEquipoGuiado();
						System.out.println("\n");
						break;
					case 2:
						LigaSantander.modEquipoGuiado();
						System.out.println("\n");
						break;
					case 3:
						LigaSantander.ficharJugador();
						System.out.println("\n");
						break;
					case 4:
						LigaSantander.rmJugadorDeEquipo();
						System.out.println("\n");
						break;
					case 5:
						System.out.println("Va a eliminar un equipo y desvincular todos sus jugadores del mismo");
						System.out.println("¿Segur@ que quiere continuar?");
						valEliminar = sacarYcomprobarYoN();

						if (valEliminar) {
							LigaSantander.rmEquipo();
						} else {
							System.out.println("Operacion cancelada.");
						}
						System.out.println("\n");
						break;
					case 6:
						crearEquipos();
						break;
					case 7:
						valMenuIni = false;
						break;
					}

				} while (valMenuIni);
				break;

			case 2:
				valMenuIni = true;

				do {
					System.out.println("Elija una opcion\n");
					System.out.println("0 - Listar Jugadores");
					System.out.println("1 - Crear Jugador");
					System.out.println("2 - Modificar Jugador");
					System.out.println("3 - Eliminar Jugador");
					System.out.println("4 - Transferir Jugador");
					System.out.println("5 - Salir a la selección de menús");
					menuGestionJugadores = sacarYcomprobarNumero(0, 5);

					switch (menuGestionJugadores) {
					case 0:
						LigaSantander.mostrarJugadores();
						System.out.println("\n");
						break;
					case 1:
						LigaSantander.addJugadorGuiado();
						System.out.println("\n");
						break;
					case 2:
						LigaSantander.modJugador();
						System.out.println("\n");
						break;
					case 3:
						LigaSantander.rmJugador();
						System.out.println("\n");
						break;
					case 4:
						LigaSantander.cambioDeEquipo();
						System.out.println("\n");
						break;
					case 5:
						valMenuIni = false;
						break;
					}

				} while (valMenuIni);
				break;

			case 3:
				int k;
				int longitudEQ;
				int longitudLista;
				int numEquipos;
				int selectorEquipos;
				int cantidadEquipos;
				boolean valEquipo;
				boolean valLista;
				String nEQ;
				int numJornadas;
				String[][] calendario;
				String[][] resultados;
				Equipo equipo1;
				Equipo equipo2;
				Equipo equipoAux;
				ArrayList<Equipo> listEquipos = new ArrayList<Equipo>();

				longitudEQ = LigaSantander.getTodosEquipos().size();
				cantidadEquipos = LigaSantander.getTodosEquipos().size();

				if (longitudEQ >= 3) {

					System.out.println("¿Cuántos equipos van a participar? (Entre 3 y " + cantidadEquipos + ")");
					numEquipos = sacarYcomprobarNumero(3, cantidadEquipos);

					LigaSantander.mostrarEquipos();

					for (int i = 1; i < numEquipos + 1; i++) {
						do {
							valEquipo = false;

							System.out.println("\n Introduce el id del equipo " + i + ":");
							selectorEquipos = sacarYcomprobarNumero(0, cantidadEquipos - 1);
							longitudLista = listEquipos.size();

							nEQ = LigaSantander.getTodosEquipos().get(selectorEquipos).getNombre();

							if (longitudLista == 0) {
								listEquipos.add(LigaSantander.getTodosEquipos().get(selectorEquipos));

							} else {
								k = 0;
								valLista = true;

								do {
									if (nEQ.equals(listEquipos.get(k).getNombre())) {

										System.out.println("No se puede añadir el mismo equipo 2 veces");
										valEquipo = true;
										valLista = false;
									}
									k++;
								} while (valLista && k < longitudLista);

								if (valLista) {
									listEquipos.add(LigaSantander.getTodosEquipos().get(selectorEquipos));
								}
							}
						} while (valEquipo);
					}

					if (numEquipos % 2 == 0) {

						numJornadas = numEquipos - 1;

						System.out.println("Con " + numEquipos + " equipos hay " + numJornadas + " jornadas");
						System.out.println();

						calendario = new String[numJornadas][listEquipos.size() / 2];
						resultados = new String[numJornadas][listEquipos.size() / 2];

						for (int i = 0; i < numJornadas; i++) {
							for (int j = 0; j < listEquipos.size() / 2; j++) {

								equipo1 = listEquipos.get(j);
								equipo2 = listEquipos.get(listEquipos.size() - j - 1);

								if (equipo1.equals(equipo2)) {
									equipo2 = listEquipos.get(listEquipos.size() - j - 2);
								}

								calendario[i][j] = equipo1.getNombre() + " vs " + equipo2.getNombre();
								resultados[i][j] = generarResultado(equipo1, equipo2);
							}

							equipoAux = listEquipos.remove(1);
							listEquipos.add(equipoAux);
						}

						for (int i = 0; i < numJornadas; i++) {
							System.out.println("--------------");
							System.out.println("Jornada " + (i + 1) + ":");
							System.out.println("--------------\n");
							for (int j = 0; j < listEquipos.size() / 2; j++) {
								System.out.println(calendario[i][j]);
								System.out.println(resultados[i][j]);
							}
							System.out.println();
						}

						System.out.println("La clasificación final de la liga ha sido:");
						Gestion.mostrarResultados(listEquipos);

					} else {
						numJornadas = numEquipos;
						System.out.println("Con " + numEquipos + " equipos hay " + numJornadas + " jornadas");
						System.out.println();

						calendario = new String[numJornadas][listEquipos.size() / 2];
						resultados = new String[numJornadas][listEquipos.size() / 2];

						for (int i = 0; i < numJornadas; i++) {
							for (int j = 0; j < listEquipos.size() / 2; j++) {
								equipo1 = listEquipos.get(j);
								equipo2 = listEquipos.get(listEquipos.size() - j - 1);

								if (equipo1.equals(equipo2)) {
									equipo2 = listEquipos.get(listEquipos.size() - j - 2);
								}

								calendario[i][j] = equipo1.getNombre() + " vs " + equipo2.getNombre();
								resultados[i][j] = generarResultado(equipo1, equipo2);

							}

							equipoAux = listEquipos.remove(0);
							listEquipos.add(equipoAux);
						}

						for (int i = 0; i < numJornadas; i++) {

							System.out.println("Jornada " + (i + 1) + ":");

							for (int j = 0; j < listEquipos.size() / 2; j++) {
								System.out.println(calendario[i][j]);
								System.out.println(resultados[i][j]);
							}
							System.out.println();
						}

						System.out.println("La clasificación final de la liga ha sido:");
						Gestion.mostrarResultados(listEquipos);
					}
					System.out.print("¿Desea eliminar la puntuación? ");
					valEliminar = sacarYcomprobarYoN();
					if (valEliminar) {
						rmPuntuacion();
					} else {
						System.out.println("La puntuación de los equipos ha sido mantenida");
					}
					System.out.println("\n");
				} else {
					System.out.println("No hay suficientes equipos para empezar la liga, el minimo son 3.\n");
				}
				break;
			case 4:

				System.out.println("Fin del programa");
				val = false;
				break;
			}
		} while (val);
	}

	public static int sacarYcomprobarNumero(int numeroMin, int numeroMax) {
		int numeroOG = 0;
		boolean valGeneral;

		Scanner lector = new Scanner(System.in);

		do {
			valGeneral = true;
			try {
				numeroOG = lector.nextInt();

			} catch (java.util.InputMismatchException ime) {
				System.out.println("Introduzca solo numeros por favor.");
				valGeneral = false;

			}
			lector.nextLine();

			if (numeroOG < numeroMin || numeroOG > numeroMax) {
				System.out.println("Introduzca un numero valido");
				valGeneral = false;
			}
		} while (!valGeneral);
		return numeroOG;
	}

	public static String sacarYcomprobarString() {
		String datos;
		boolean val;
		boolean val2;

		Scanner lector = new Scanner(System.in);

		do {
			val = true;
			datos = lector.nextLine();

			if (datos == null || datos.equals("")) {
				val = false;
				System.out.println("Introduzca algun contenido.");

			} else {
				val2 = false;

				if (datos.matches("[a-zA-Z]+")) {
					val2 = true;
				}
				if (!val2) {
					System.out.println("Solo se permiten letras");
					val = false;
				}
			}
		} while (!val);
		return datos;
	}

	public static boolean sacarYcomprobarYoN() {
		char caracter;
		String cadena;
		boolean comprobante = true;
		boolean comprobanteBucle;

		Scanner lector = new Scanner(System.in);

		System.out.println("\033[32m" + "Y" + "\u001B[0m" + "/" + "\033[31m" + "N" + "\u001B[0m");

		do {
			cadena = lector.nextLine();
			cadena = cadena.toUpperCase();
			caracter = cadena.charAt(0);
			comprobanteBucle = false;

			if (caracter == 'Y') {
				comprobante = true;
			} else if (caracter == 'N') {
				comprobante = false;
			} else {
				System.out.println("Introduzca un caracter valido");
				comprobanteBucle = true;
			}
		} while (comprobanteBucle);
		return comprobante;
	}

	private void crearEquipos() {
		LigaSantander.addEquipo("Real Madrid");
		LigaSantander.addEquipo("Barcelona");
		LigaSantander.addEquipo("Manchester United");
		LigaSantander.addEquipo("Bayern Munich");
		LigaSantander.addEquipo("Juventus");
		LigaSantander.addEquipo("Paris Saint-Germain");
		LigaSantander.addJugador("Real Madrid", "Sergio", "Ramos", "2ºA");
		LigaSantander.addJugador("Barcelona", "Lionel", "Messi", "1ºB");
		LigaSantander.addJugador("Manchester United", "Bruno", "Fernandes", "2ºB");
		LigaSantander.addJugador("Bayern Munich", "Robert", "Lewandowski", "2ºA");
		LigaSantander.addJugador("Juventus", "Cristiano", "Ronaldo", "1ºA");
		LigaSantander.addJugador("Paris Saint-Germain", "Kylian", "Mbappé", "2ºB");
	}

	private static String generarResultado(Equipo equipo1, Equipo equipo2) {

		int numAleatorio = (int) (Math.random() * (1 - 0 + 1));

		if (numAleatorio == 0) {
			equipo1.setPuntos(equipo1.getPuntos() + 1);
			return ("\033[32m" + "El equipo " + equipo1.getNombre() + " ha ganado.\n" + "\u001B[0m");
		} else {
			equipo2.setPuntos(equipo2.getPuntos() + 1);
			return ("\033[32m" + "El equipo " + equipo2.getNombre() + " ha ganado.\n" + "\u001B[0m");
		}
	}

	private static void mostrarResultados(ArrayList<Equipo> datos) {
		datos.sort((e1, e2) -> e2.getPuntos() - (e1.getPuntos()));

		for (int i = 0; i < datos.size(); i++) {

			System.out.println(datos.get(i).getNombre() + " - " + datos.get(i).getPuntos());
		}
	}

	private void rmPuntuacion() {
		int longitudi = LigaSantander.getTodosEquipos().size();
		for (int i = 0; i < longitudi; i++) {
			LigaSantander.getTodosEquipos().get(i).setPuntos(0);
		}
	}
}