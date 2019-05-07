package dominio;

import java.util.Objects;
import java.util.Vector;

public class Estado {
	private Nodo nodoActual;
	private Vector<Nodo> destinos;
	
	//Estado inicial
	public Estado (Nodo nodoActual, Vector<Nodo> nodosDestinos){
		this.nodoActual = nodoActual;
                this.destinos=(Vector<Nodo>)nodosDestinos.clone();
	}


	public Nodo getNodoActual() {
		return nodoActual;
	}

	public void setNodoActual(Nodo nodoActual) {
		this.nodoActual = nodoActual;
	}


	public Vector<Nodo> getDestinos() {
		return destinos;
	}

	public void setDestinos(Vector<Nodo> destinos) {
		this.destinos = destinos;
	}


	
      

        @Override
        public String toString() {
            return "Estado {" + " nodoActual = " + nodoActual + ","
                    + " destinos = " + destinos + "}";
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.nodoActual);
            hash = 37 * hash + Objects.hashCode(this.destinos);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Estado other = (Estado) obj;
            if (!Objects.equals(this.nodoActual, other.nodoActual)) {
                return false;
            }
            if (!Objects.equals(this.destinos, other.destinos)) {
                return false;
            }
            return true;
        }

        
}
