/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp.metier;

import java.util.Objects;

/**
 *
 * @author perussel
 */
public abstract class Action {

    //Attribut
    //Nom de l'action
    private String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    //Constructeur
    protected Action(String libelle) {
        this.libelle = libelle;
    }

    //Renvoie la valeur de l'action le jour j
    public abstract float valeur(Jour j);

    @Override
    //Renvoie le hash de la fonction
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    //Vérifie si l'action est égale à une autre action
    public boolean equals(Object obj) {
        

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Action other = (Action) obj;
        return Objects.equals(this.libelle, other.libelle);
        
    }

    //Renvoie le string de l'action
    public String toString() {
        return this.getLibelle();
    }
}
