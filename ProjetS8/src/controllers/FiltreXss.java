package controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Mouhamad
 * Cette classe permet de filtrer les entrées de l'utilisateur en vérifiant les paramètres qu'il a entré et en
 * supprimant les tags HTML.
 * Seules les méthodes getParameter et get Header ont été redéfinies ici car étant les seules utilisées dans le projet
 * @version 1.0
 */

//On définit le nom du filtre et l'ensemble des classes auxquelles il s'applique. Ici, c'est toutes les classes
@WebFilter(filterName = "FiltreXss", urlPatterns = {"/*"} )
public class FiltreXss implements Filter {

	//Initialisation du filtre
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //Association avec le request et la réponse de la servlet concernée
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter( new XssRequestWrapper( (HttpServletRequest) request), response );
    }
    
    //Méthode à appeler pour la destruction.. là elle n'est pas définie
    @Override
    public void destroy() {
    }


    private static class XssRequestWrapper extends HttpServletRequestWrapper {


    	//enum comprenant les differents pattern,s à filtrer, il s'agit des tags HTML, des expressions du genre
    	// &lt, &gt et des hexadécimaux (pour eviter le remplacment avec du code ASCII des < ou > par exemple)
        private static final Pattern [] PATTERNS_A_FILTRER = {
                Pattern.compile( "<.*?>" ),
                Pattern.compile( "&.*?;" ),
                Pattern.compile( "%[0-9a-fA-F]*" )
        };
        

        public XssRequestWrapper(HttpServletRequest servletRequest) {
            super(servletRequest);
        }

        @Override
        public String[] getParameterValues( String parameterName ) {
            
            String [] values = super.getParameterValues(parameterName);

            if (values == null) return null;

            int count = values.length;
            for ( int i = 0; i < count; i++ ) {
                // On remplace chaque chaîne de caractères
                values[i] = securiserChaine(values[i]);
            }

            return values;
        }
 
        /**
         *On redéfinit la méthode getParameter pour supprimer les tags HTML et renvoyer la chaine sécurisée
         *@param	parameter le paramètre à vérifier
         *@return le parametre sécurisé grâce à la méthode securiserChaine 
         */
        @Override
        public String getParameter( String parameter ) {
            return securiserChaine( super.getParameter(parameter) );
        }

        /**
         *On redéfinit la méthode getHeader pour supprimer les tags HTML et renvoyer la chaine sécurisée
         *@param	name le paramètre à vérifier
         *@return le parametre sécurisé grâce à la méthode securiserChaine 
         */
        @Override
        public String getHeader( String name ) {
            return securiserChaine( super.getHeader(name) );
        }

        /**
         *On vérifie que la chaîne n'est pas nulle et on supprime tous les caractères qui permettent d'avoir un tag HTML
         *@param	value la chaîne à sécuriser
         *@return la chaîne sécurisée
         */
        private String securiserChaine( String value ) {
            if ( value != null ) {
                // On retire le code ASCII 0, au cas ou
                value = value.replaceAll( "\0", "" );

                // Supprime l'ensemble de tags et des entités existants
                for ( Pattern pattern : PATTERNS_A_FILTRER ) {
                    value = pattern.matcher( value ).replaceAll( "" );
                }
                
                // Au cas ou les caractères < et > ne seraient pas en nombres pairs
                value = value.replaceAll( "<", "" );
                value = value.replaceAll( ">", "" );
            }
            return value;
        }
    }

}
