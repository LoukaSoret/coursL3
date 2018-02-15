/*
 * Armoroides - casse briques à but pédagogique
 * Copyright (C) 2016 Guillaume Huard
 * 
 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).
 * 
 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.
 * 
 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.
 * 
 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

// Associe un objet à une valeur de propriété
// (table de hashage du pauvre, voir bibliothèque standard pour mieux)
class Association {
    String propriete, valeur;
    Object objet;
    
    Association(String p, String v, Object o) {
        propriete = p;
        valeur = v;
        objet = o;
    }
}

public class Configuration {
    static Properties prop;
    static HashMap<String, Object> associations;
    static Logger logger;
    
    public static void associe(String p, String v, Object o) {
        if (associations == null)
            associations = new HashMap<>();
        associations.put(p+"/"+v, o);
    }
    
    public static Logger logger() {
    		if (logger == null) {
    			logger = Logger.getLogger("Armoroides.Logger");
    			logger.setLevel(Level.parse(lisString("LogLevel")));
    		}
    		return logger;
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T trouve(String prop) {
        String val = lisString(prop);
        Object result = associations.get(prop+"/"+val);
        if (result == null) {
            throw new NoSuchElementException("Pas d'objet correspondant à (" + prop
                + ", " + val + ")");
        }
        return (T) result;
    }

    static void chargerProprietes(Properties p, InputStream in, String nom) {
        try {
            p.load(in);
            System.err.println("Fichier de configuration " + nom + " lu.");
        } catch (IOException e) {
            System.err.println("Impossible de charger " + nom);
            System.err.println(e);
            System.exit(1);
        }
    }

    static Properties proprietes() {
        Properties p;
        InputStream in = Chargeur.donnees("defaut.cfg");
        Properties defaut = new Properties();
        chargerProprietes(defaut, in, "defaut.cfg");
        String nom = System.getProperty("user.home") + "/.armoroides";
        try {
            in = new FileInputStream(nom);
            p = new Properties(defaut);
            chargerProprietes(p, in, nom);
        } catch (FileNotFoundException e) {
            p = defaut;
        }
        return p;
    }

    public static String lisString(String nom) {
        if (prop == null)
            prop = proprietes();
        String value = prop.getProperty(nom);
        if (value != null)
            return value;
        else
            throw new NoSuchElementException("Propriété "+nom+" manquante");
    }
    
    public static float lisFloat(String nom) {
        String value = lisString(nom);
        return Float.parseFloat(value);
    }
    
    public static int lisInt(String nom) {
        String value = lisString(nom);
        return Integer.parseInt(value);
    }

    public static boolean lisBoolean(String nom) {
        String value = lisString(nom);
        return Boolean.parseBoolean(value);
    }
}
