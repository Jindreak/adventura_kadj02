/* Soubor je ulozen v kodovani UTF-8.dslkaslkdj;salkjd;lkasjd;klasj;lkd
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Jindreak.adventura_kadj02.main;




import com.github.Jindreak.adventura_kadj02.logika.*;
import com.github.Jindreak.adventura_kadj02.uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        
        if (args.length < 1) {
            ui.hraj();
        } else {
            ui.hrajZeSouboru(args[0]);
        }
    }
    
    private Start() {}
}
