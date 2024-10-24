package cat.iesesteveterradas.fites;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Objectius:
 *   Implementa els mètodes: comprovaExistenciaFitxer, determinaSiEsOcult, obtenirDataUltimaModificacio, verificarEsPotModificar, llistarArxiusDirectori 
 * 
 *   La funcionalitat que s'espera que realitzin és:
 * 
 * - Comprovar si `filePath` existeix.
 * - Determinar si és ocult.
 * - Obtenir la data de la darrera modificació.
 * - Verificar si es pot modificar.
 * - Llistar els arxius del directori pare de `filePath`.
 */

public class Exercici0 {

    private String filePath;

    public static void main(String args[]) {
        Exercici0 exercici = new Exercici0();
        // Configurar la ruta del fitxer
        exercici.configuraRutaFitxer(System.getProperty("user.dir") + "/data/exercici0/Exercici0.dat");
        // Executar la lògica principal
        exercici.executa();
    }

    // Mètode que conté la lògica principal
    public void executa() {
        boolean existeix;

        existeix = comprovaExistenciaFitxer();
    
        System.out.println("El fitxer existeix: " + existeix);

        if (existeix) {
            boolean ocult = determinaSiEsOcult();
            System.out.println("El fitxer és ocult: " + ocult);

            Date modificat = obtenirDataUltimaModificacio();
            DateFormat formatData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String strModificat = formatData.format(modificat);
            System.out.println("Última modificació: " + strModificat);

            boolean esPotModificar = verificarEsPotModificar();
            System.out.println("El fitxer es pot modificar: " + esPotModificar);
        } else {
            System.out.println("El fitxer no existeix per determinar si és ocult, mostrar la data de l'última modificació o verificar si es pot modificar.");
        }

        ArrayList<String> llistaArxius = llistarArxiusDirectori();
        if (llistaArxius != null) {
            System.out.println("La llista de fitxers d'aquesta carpeta és: " + llistaArxius);
        } else {
            System.out.println("El directori base no existeix o no és un directori.");
        }
    }

    // Mètode per comprovar l'existència del fitxer
    public Boolean comprovaExistenciaFitxer() {
        // *************** CODI EXERCICI FITA **********************/
        File fitxer = new File(this.filePath);

        if (fitxer.exists() && fitxer.isFile()) {
            return true;  // Arxiu existeix
        }
        return false;
    }

    // Mètode per determinar si el fitxer és ocult
    public Boolean determinaSiEsOcult() {
        // *************** CODI EXERCICI FITA **********************/
        File fitxer = new File(this.filePath);

        return fitxer.isHidden(); 
    }

    // Mètode per obtenir la data de l'última modificació
    public Date obtenirDataUltimaModificacio() {
        // *************** CODI EXERCICI FITA **********************/
        File fitxer = new File(this.filePath);

        long lastModified = fitxer.lastModified();
        Date date = new Date(lastModified);

        return date; // A substituir  
    }

    // Mètode per verificar si el fitxer es pot modificar
    public Boolean verificarEsPotModificar() {
        // *************** CODI EXERCICI FITA **********************/
        File fitxer = new File(this.filePath);

        return fitxer.canWrite(); // A substituir 
    }

    // Mètode per llistar els fitxers del directori base
    public ArrayList<String> llistarArxiusDirectori() {
        // *************** CODI EXERCICI FITA **********************/

        File directori = new File(this.filePath);
        String  parentDirectoryName = directori.getParent();
        File parentDirectory = new File(parentDirectoryName);

        ArrayList<String> fitxersDirectori = new ArrayList<>();

        String[] arxius = parentDirectory.list();
        if (arxius != null && arxius.length > 0) {
            for (String arxiu : arxius) {

                fitxersDirectori.add(arxiu);
            }
        }

        return  fitxersDirectori;
    }

    /****************************************************************************/
    /*                          NO CAL MODIFICAR                                */
    /****************************************************************************/
    // Mètode per configurar la ruta del fitxer
    public void configuraRutaFitxer(String filePath) {
        this.filePath = filePath;
    }
}
