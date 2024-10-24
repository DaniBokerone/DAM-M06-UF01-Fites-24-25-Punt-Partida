package cat.iesesteveterradas.fites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementa codi que compleixi el següent:
 * 
 * - Llegeix un fitxer de text d'entrada amb codificació UTF-8.
 * - Inverteix el text de cada línia utilitzant el mètode 'giraText'.
 * - Escriu les línies invertides en un fitxer de sortida amb codificació UTF-8.
 * - El fitxer de sortida es crea o sobrescriu si ja existeix.
 * - Si hi ha un error en llegir o escriure els fitxers, l'excepció es mostra a la consola.
 */
public class Exercici1 {

    private String filePathIn;
    private String filePathOut;


    public static void main(String args[]) {
        Exercici1 exercici = new Exercici1();
        // Configurar els fitxers d'entrada i sortida
        exercici.configuraRutaFitxerEntrada(System.getProperty("user.dir") + "/data/exercici1/Exercici1Entrada.txt");
        exercici.configuraRutaFitxerSortida(System.getProperty("user.dir") + "/data/exercici1/Exercici1Solucio.txt");
        // Executar la lògica principal
        exercici.executa();
    }    

    // Processa el fitxer d'entrada i genera el fitxer de sortida.
    public void executa() {
        // *************** CODI EXERCICI FITA **********************/

        Path origen = Paths.get(this.filePathIn);
        Path desti = Paths.get(this.filePathOut);

        if (!Files.exists(origen) || !Files.isRegularFile(origen)) {
            System.out.println("Error: L'arxiu d'origen no existeix o no és un arxiu de text.");
            return;
        }

        // Comprovar si l'arxiu destí ja existeix
        if (Files.exists(desti)) {
            System.out.println("Advertència: L'arxiu de destí ja existeix i serà sobreescrit.");
        }

        try (BufferedReader reader = Files.newBufferedReader(origen, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(desti, StandardCharsets.UTF_8)) {

            String linia;
            while ((linia = reader.readLine()) != null) {
                String liniaReversa = giraText(linia);
                writer.write(liniaReversa);
                writer.newLine(); 
            }

            if (Files.size(origen) > 0) {
                long totalLines = Files.lines(origen, StandardCharsets.UTF_8).count();
                if (Files.lines(origen).skip(totalLines - 1).findFirst().get().isEmpty()) {
                    writer.newLine(); 
                }
            }

            System.out.println("La còpia s'ha realitzat correctament.");

        } catch (IOException e) {
            System.out.println("Error en copiar l'arxiu: " + e.getMessage());
        }

    }

    // Mètode per invertir el text d'una línia
    public static String giraText(String text) {
        // *************** CODI EXERCICI FITA **********************/

        char ch ;
        String reverseText ="";
        for (int i=0; i< text.length(); i++)
        {
            ch= text.charAt(i); 
            reverseText= ch+reverseText; 
        }
        return reverseText; 
    }

    /****************************************************************************/
    /*                          NO CAL MODIFICAR                                */
    /****************************************************************************/
    // Mètode per configurar el fitxer d'entrada
    public void configuraRutaFitxerEntrada(String filePathIn) {
        this.filePathIn = filePathIn;
    }

    // Mètode per configurar el fitxer de sortida
    public void configuraRutaFitxerSortida(String filePathOut) {
        this.filePathOut = filePathOut;
    }
}
