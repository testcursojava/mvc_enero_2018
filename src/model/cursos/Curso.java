package model.cursos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import model.questions.StatusResponse;

public class Curso implements ICurso {
	
	private HashMap<String,Alumno> alumnos;
	private Profesor profesor;
	
	public Curso() {
		alumnos = new HashMap<String,Alumno>();
	}

	@Override
	public void matricula(Alumno alumno) {
		alumnos.put(alumno.getNif(), alumno);
	}

	@Override
	public void pasarLista(HashMap<String, Boolean> asistencia) {
		Iterator<String> nifs = asistencia.keySet().iterator();
		while(nifs.hasNext()) {
			String nif = nifs.next();
			Alumno al = this.buscarAlumno(nif);
			if(al!=null)
				al.setAsistencia(asistencia.get(nif));
		}
	}

	@Override
	public void evaluar(int evaluacion, HashMap<String, Float> notas) {
		Iterator<String> nifs = notas.keySet().iterator();
		if(profesor!=null) {
			while(nifs.hasNext()) {
				String nif = nifs.next();
				profesor.evalua(this.buscarAlumno(nif),evaluacion,notas.get(nif));
			}
		}
		
	}

	@Override
	public Alumno buscarAlumno(String nif) {
		// TODO Auto-generated method stub
		return alumnos.get(nif);
	}

	@Override
	public void setProfesor(Profesor profe) {
		profesor = profe;
	}

	@Override
	public void setAsistencia(Asistencia asistencia) {
		Alumno al = buscarAlumno(asistencia.getNif());
		if(al!=null)
			al.setAsistencia(asistencia.isAsistencia());
	}

	@Override
	public void setEvaluacion(InfoEvaluacion ev) {
		if(profesor!=null)
			profesor.evalua(buscarAlumno(ev.getNif()), ev.getEvaluacion(), ev.getNota());
	}

	@Override
	public boolean borrarAlumno(String nif) { //funcion que borra el nif del objeto alumnos
	
		if(alumnos.containsKey(nif)) { //si existe ese nif
     		alumnos.remove(nif); //borralo
		 	System.out.println("Alumno borrado");
		
			return true;} //devuelve ok
		else 
			System.out.println("El alumno no se encuentra");
			return false; //devuelve error
		
			
		}

	@Override
	public boolean Salir(String exit) {
		// TODO Auto-generated method stub
		return false;
	}
		
	}
	
	



