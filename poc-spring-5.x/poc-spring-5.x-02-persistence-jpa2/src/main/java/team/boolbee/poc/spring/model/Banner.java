package team.boolbee.poc.spring.model;

import java.util.Date;

public class Banner {

	private int id;
	private String title;
	private Date date; // Fecha de Publicacion del Banner
	private String filename; // atributo para guardar el nombre de la imagen
	private Status status;
	
	/**
	 * Constructor de la clase
	 */
	public Banner() {		
		this.date = new Date(); // Por default, la fecha del sistema
		this.status = Status.ACTIVE; // Por default el banner esta activo
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", title=" + title + ", date=" + date + ", filename=" + filename + ", status="
				+ status + "]";
	}
}