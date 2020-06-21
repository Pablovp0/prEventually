package prEventually;

public class Participación {

	int idEv;
	String nU;
	int id;

	public Participación(int i, int idEvento, String u) {
		id = i;
		idEv = idEvento;
		nU = u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEv() {
		return idEv;
	}

	public String getnU() {
		return nU;
	}

	public void setIdEv(int idEv) {
		this.idEv = idEv;
	}

	public void setnU(String nU) {
		this.nU = nU;
	}

}
