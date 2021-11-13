package com.umanizales.lists_prog2.model.listade;
/**
 * Clase que me permite gestionar una lista Doblemente enlazada
 * contiene los métodos u operaciones
 * solo cuenta con el atributo head = que representa el primer niño
 * tiene un contador para saber cuantos niños hay en las listas
 * @author Carlos Arias
 */
import com.umanizales.lists_prog2.exception.ListaDeException;


import com.umanizales.lists_prog2.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.umanizales.lists_prog2.model.Gender.FEMENINO;
import static com.umanizales.lists_prog2.model.Gender.MASCULINO;


@Data
@NoArgsConstructor
public class ListaDE {
    /**
     * Atributo que representa el inicio de la lista
     */
    private Node head;
    /**
     * Atributo que nos permite guardar una serie de datos
     */
    private int count;

    /**
     * Metodo que adiciona un niño al final de la lista
     *
     * @param boy es el parametro que estamos usando
     * @throws ListaDeException metodo para expresar el mensaje o exepcion
     */
    public void addDe(Boy boy) throws ListaDeException {
        /**
         * si la cabeza esta vacia
         */
        Boy boyExist = findByIdentificationDe(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si el niño ya existe se envia un mensaje al usuario
             * para que sepa que ya existe la identificaion ingresada
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        /**
         * si la cabeza esta vacia el nuevo niño es la cabeza
         */
        if (this.head == null) {
            this.head = new Node(boy);
        } else {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            while (temp.getNext() != null) {
                /**
                 * el ayudante se pasa al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * quedo parado en el ultimo y le digo a siguiente
             * que tome a vacio y al anterior que tome al ayudante
             */
            temp.setNext(new Node(boy));
            temp.getNext().setNext(temp);
        }
        count++;
    }

    /**
     * Método que me busca en la lista simplemente enlazada, un niño a partir de la identificación
     * Si no encuentra el niño retorna vacío (null)
     *
     * @param identification Cédula, TI, CE , Sisben que identifica el niño que voy a buscar
     * @return El niño que encontré con todos sus datos
     */
    public Boy findByIdentificationDe(String identification) {
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = head;
        /**
         * Creo un ccilo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda para en vacío (null)
         */
        while (temp != null) {
            /**
             * Pregunto si el niño en el cual está ubicado mi ayudante es el de la identificación
             * que estoy buscando ingresado en el parámetro identificacion
             */
            if (temp.getData().getIdentification().equals(identification)) {
                /**
                 * Lo encontré y lo retorno de inmediato
                 * Finaliza mi método
                 */
                return temp.getData();
            }
            /**
             * Mi ayudante se pasa al siguiente nodo
             */
            temp = temp.getNext();
        }
        /**
         * Si llega a esta línea significa que no encontré el niño y retorno vacío
         */
        return null;
    }

    /**
     * metodo por el cual validaremos que la lista este vacia
     *
     * @throws ListaDeException
     */
    public void validateListEmptyDe() throws ListaDeException {
        /**
         * si en la cabeza o inicio de la lista es igual a vacio (null)
         */
        if (this.head == null) {
            /**
             * generamos el mensaje de exepcion para indicarle al usuario que la lista esta vacia
             */
            throw new ListaDeException("No hay datos en la lista");
        }
    }

    public List<Boy> listDe() throws ListaDeException {
        if (this.head != null) {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            while (temp != null) {
                list.add(temp.getData());
                temp = temp.getNext();
            }
            return list;
        }
        throw new ListaDeException("No hay datos en la lista");
    }

    /**
     * creamos una variable llamada contador el cual contendra nuemeros enteros para guardar la cantidad de niños
     */
    public int countDe() {
        /**
         * iniciamos el contador en cero
         */
        int count = 0;
        /**
         * si en la cabeza hay algo y no esta vacia iniciamos el ciclo
         */
        if (this.head != null) {
            // Recorrer la lista principal de principio a fin
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            ListaDE listTemp = new ListaDE();
            Node temp = this.head;
            /**
             * mientras el ayudante no vea que el brazo este vacio sigue recorriendo la lista
             */
            while (temp != null) {
                /**
                 * le sumamos uno al contador
                 */
                count++;
                /**
                 * el ayudante pasa al siguiente
                 */
                temp = temp.getNext();
            }
        }
        /**
         * termino el metodo y retorno el contador con la cantidad de niños encontrados
         */
        return count;
    }

    /**
     * metodo por el cual agregamos un nuedo nodo o niño a la lista en el inicio de la lista
     *
     * @param boy
     * @throws ListaDeException
     */
    public void addToStartDe(Boy boy) throws ListaDeException {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentificationDe(boy.getIdentification());
        if (boyExist != null) {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        /**
         * si la cabeza esta vacia
         */
        if (this.head == null) {
            /**
             * el nuevo dato que se esta ingresando sera la cabeza o inicio de la lista
             */
            this.head = new Node(boy);
            this.head.setPrevious(null);
        } else {
            /**
             * el ayudante tomara al nuevo nodo y ara que el nuevo nodo tome a la cabeza
             * por lo cual el nuevo nodo sera la cabeza
             */
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head = temp;
            this.head.setPrevious(head);
        }
        count++;
    }

    /**
     * metodo por el caul agregaremos un nodo en una posicion dada
     *
     * @param boy
     * @param positon
     * @throws ListaDeException
     */
    public void addByPositionDe(Boy boy, int positon) throws ListaDeException {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentificationDe(boy.getIdentification());
        if (boyExist != null) {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaDeException("La identificacion ingresada ya existe");
        }
        /**
         * validamos la posicion verificando que si este esa posicion en la lista
         */
        if (positon > count) {
            this.addDe(boy);
            return;
        }
        /**
         * si la posicion es la primera metermoes el nodo en la cabeza de la lista
         */
        if (positon == 1) {
            addToStartDe(boy);
        } else {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             * y el contador inicia en 1
             */
            int cont = 1;
            Node temp = this.head;
            /**
             * mientras el ayudante no vea que el siguiente sea vacio
             */
            while (temp != null) {
                /**
                 * si se valida que el contador queda en una posicion negativa se rompe el ciclo
                 */
                if (cont == positon - 1) {
                    break;
                }
                /**
                 * Mi ayudante se pasa al siguiente nodo
                 */
                temp = temp.getNext();
                /**
                 * aumento el contador en uno para saber el total de los niños
                 */
                cont++;
            }
            /**
             * mi ayudante va a estar posicionado en la posicion anterior y adicionara el nodo
             * en el brazo siguiente y el anterior tomara al ayudante
             */
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(this.head);
            nodeNew.getNext().setPrevious(nodeNew);
            nodeNew.setPrevious(temp);
            count++;
        }
    }

    public void invertDe() throws ListaDeException {
        validateListEmptyDe();
        /**
         * preguntamos si en la cabeza hay algo y no esta vacia iniciamos el ciclo
         */
        if (this.head != null) {
            /**
             * creamos una nueva lista temporal en la cual colocaremos los nodos del final al inicio
             */
            ListaDE listTemp = new ListaDE();
            // Recorrer la lista principal de principio a fin
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * mientras el ayudante no vea que el brazo este vacio sigue recorriendo la lista
             */
            while (temp != null) {
                /**
                 *en la lista temporal colocaremos al inicio el dato que tiene el ayudante
                 */
                listTemp.addToStartDe(temp.getData());
                /**
                 * el ayudante pasa al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * la cabeza ahora sera la lista temporal y sera la nueva lista
             */
            this.head = listTemp.getHead();
        }
    }

    /**
     * Método que me permita eliminar un niño dada una posición
     *
     * @param boy
     * @param position
     * @throws ListaDeException
     */
    public void deleteByPositionDe(Boy boy, int position) throws ListaDeException {
        /**
         * Validación de la posicíon, si la posición que se ingresó es mayor a mi contador
         */
        if (position > count) {
            this.addDe(boy);
            return;
            //throw  new ListaSeException("La posición ingresada no es válida");
        }
        /**
         * en caso de que sea la posición 1, es decir la cabeza
         */
        if (position == 1) // en caso de que sea la posición 1, es decir la cabeza
        {
            this.head = null;
        } else {
            /**
             * inicio mi contador en 1 porque si entró acá quiere decir que por lo menos hay uno
             */
            int cont = 1;
            /**
             * Creo mi ayudante y lo paro en la cabeza
             */
            Node temp = this.head;
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                if (count == position - 1) {
                    /**
                     * pongo a mi ayudante a apuntar al siguiente de su siguiente
                     */
                    temp = temp.getNext().getNext();
                    /**
                     *  a su previous le digo que tome a mi ayudante
                     */
                    temp.getNext().setPrevious(temp);
                    /**
                     * rompe el ciclo
                     */
                    break;
                } else {
                    /**
                     * sino incremente mi ayudante para que entre a nueva iteración
                     */
                    count++;
                }
            }

        }
    }

    /**
     * Método creado para listar los niños de un género que me ingresan
     *
     * @param gender entra como parámetro para listar los niños del género
     * @return // retorna el listado de niños que pertenecen a ese género
     * @throws ListaDeException
     */
    public ListaDE getListDeBoysByGenderDe(Gender gender) throws ListaDeException {
        validateListEmptyDe();
        Node temp = this.head;
        ListaDE listTemp = new ListaDE();
        while (temp != null) {
            if (temp.getData().getGender().equals(gender)) {
                listTemp.addDe(temp.getData());
            }
            temp = temp.getNext();
        }
        return listTemp;
    }

    public void variantBoys() throws ListaDeException {
        validateListEmptyDe(); // valido que en mi lista existan datos
        ListaDE kids = this.getListDeBoysByGenderDe(MASCULINO);
        ListaDE girls = this.getListDeBoysByGenderDe(FEMENINO);
        ListaDE minList = null;
        ListaDE maxList = null;
        if (kids.getCount() > girls.getCount()) {
            minList = girls;
            maxList = kids;
        } else {
            minList = kids;
            maxList = girls;
        }
        Node temp = minList.getHead();
        int pos = 2;
        while (temp != null) {
            maxList.addByPositionDe(temp.getData(), pos);
            pos = pos + 2;
            temp = temp.getNext();
        }
        this.head = maxList.getHead();
    }

    public void deleteByGenderDe(Gender gender) throws ListaDeException {
        /**
         * llamamos el metodo par validar si la lista esta vacia
         */
        validateListEmptyDe();
        /**
         * si la cabeza es diferente a vacio llamo al ayudante para empezar a recorrer la lista
         */
        if (this.head != null) {
            /**
             * paramos al ayudante en la cabeza
             */
        }
        Node temp = this.head;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * validamos si el ayudante con el dato y el genero que trae es el genero del niño
             */
            if (temp.getData().getGender().equals(gender)) ;
            {
                /**
                 * eliminamos el dato con el genero para eliminar el niño
                 */
                deleteDe(temp.getData().getIdentification());
                temp = temp.getNext().getNext();
                temp.getNext().setPrevious(temp);
            }
            /**
             * ayudante pasa al siguiente
             */
            temp = temp.getNext();
        }
        /**
         * envio un mensaje al usuario informando que la lista esta vacia
         */
        throw new ListaDeException("la lista esta vacia");
    }

    public void deleteDe(String identification) throws ListaDeException {
        if (this.head != null)
        {
            if(this.head.getData().getIdentification().equals(identification))
            {
                this.head = this.head.getNext();
            }
            else
            {
                Node temp = this.head;
                while(temp!=null)
                {
                    if(temp.getNext() != null && temp.getNext().getData().getIdentification().equals(identification))
                    {
                        break;
                    }
                    temp= temp.getNext();
                    temp.getNext().setPrevious(temp);
                }
                if(temp!= null)
                {
                    temp.setNext(temp.getNext().getNext());
                    temp.getNext().setPrevious(temp);
                }
                else {
                    throw  new ListaDeException("La identificación "+identification + " no existe en la lista");
                }
            }
        }
        else
        {
            throw  new ListaDeException("No hay datos en la lista");
        }
    }
  public void deleteSuicide (String identification) throws ListaDeException {
      if (this.head != null){
          if (this.head.getData().getIdentification().equals(identification)){
              this.head = this.head.getNext();
              if (this.head != null){
                  this.head.setPrevious(null);
              }
              count--;
          }else {
              Node temp = this.head;
              while (temp != null){
                  if (temp != null && temp.getData().getIdentification().equals(identification)){
                      temp.getPrevious().setNext(temp.getNext());
                      if(temp.getNext() != null){
                          temp.getNext().setPrevious(temp.getPrevious());
                      }
                      count--;
                      break;
                      }
                  temp = temp.getNext();
                  }
              if (temp == null){
                  throw new ListaDeException("La identificacion "+ identification + "no existe");
              }
            }
          }else {
          throw new ListaDeException("No hay datos en la lisata");
      }
  }
    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños
     * @param code entra como parámetro con el código que identifica la ciudad
     * @return me retorna el contador con la cantidad de datos obtenidos en el método
     **/
    public int getCountBoysByLocationDe(String code) {
        Node temp= this.head;
        int count=0;
        while(temp != null)
        {
            if(temp.getData().getLocation().getCode().equals(code))
            {
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }
    public void changeXtremesDe() throws  ListaDeException{
        if (this.head != null && this.head.getNext() != null) {
            Boy start = this.head.getData();
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            this.head.setData(temp.getData());
            temp.setData(start);
        }
        else
        {
            throw  new ListaDeException("NO es posible ejecutar el cambio de extremos");
        }
    }

    /**
     * Metodo que recibe como parametro el genreo de un niño y lista los niños de ese genero
     * @param gender
     * @return
     * @throws ListaDeException
     */
    public ListaDE getListTheBoysByGenderDe (Gender gender)throws ListaDeException{
     validateListEmptyDe();
     Node temp = this.head;
     ListaDE listTemp = new ListaDE();
     while (temp != null){
         if (temp.getData().getGender().equals(gender)){
             listTemp.addDe(temp.getData());
         }
         temp = temp.getNext();
     }
     return listTemp;
}

    /**
     *
     * @param grade
     * @return
     */
    public List<Boy> listBoysGradeDe(byte grade) {
        /**
         * si la cabeza es diferente a vacio llamo al ayudante para empezar a recorrer la lista
         */
        if (this.head != null) {
            Node temp = this.head;
            temp.getNext().setPrevious(null);
            /**
             * inicio una nueva lista para guardar los datos que necesito
             */
            List<Boy> list = new ArrayList<>();
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                /**
                 * le decimos al ayudante temporal que pregunte al niño en el que esta si el grado indicado es igual al garo al que pertenece.
                 */
                if (temp.getData().getGrade() == grade) {
                    /**
                     * Si el grado indicado es igual agregamos el niño a lista
                     */
                    list.add(temp.getData());
                }
                /**
                 * el ayudante pasa al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * Retornamso el la lista con los niños del grado solicitado
             */
            return list;
        }
        /**
         * termino el ciclo y retorno vacio
         */
        return null;
    }
    /**
     * Método que me retorne un listado con el conteo de niños por Genero
     * @param gender
     * @return
     */
    public int getCountBoysByGenderDe (Gender gender) {
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio adicional llevo un contador
         */
       Node temp = this.head;
        int count = 0;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null)
        {
            /**
             * si en ayudante con el dato del genero es igual al genero solicitado
             * llevelo a contador y al contador se le suma 1
             */
            if(temp.getData().getGender().equals(gender));
            {
                count++;
            }
            /**
             * ayudante pasa al siguiente
             */
            temp = temp.getNext();
        }
        /**
         * retorno el contador
         */
        return count;
    }
    /**
     * Método que me permita eliminar los niños con una edad mayor a la suministrada
     */
    public void deleteByAgeOlderDe (byte age)throws ListaDeException {
        validateListEmptyDe();
        /**
             * paramos al ayudante en la cabeza
             */
        Node temp = this.head;
        /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
        while (temp != null) {
          /**
                 * validamos si el ayudante con el dato y la edad que trae es menor a la edad del niño
                 */
          if (temp.getData().getAge() > age);
             {
               deleteDe(temp.getData().getIdentification());
             }
                /**
                 * ayudante pasa al siguiente
                 */
                temp = temp.getNext();
            }
        }
    /**
     *
     Método que, dado un género y una edad, me deje al inicio de la lista los niños del género dado y con la edad
     menor o igual a la entregada
     * @param age
     *
     */
    public void listByGenderAgeDe(Gender gender, byte age) throws ListaDeException {
        validateListEmptyDe();
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * si el dato que tiene el ayudante de genero es igual al genero y el dato de la edad es menoro
             * igual a la edad del niño
             */
            if (temp.getData().getGender().equals(gender) && temp.getData().getAge()
                    <= age)
            {
                Boy boy = temp.getData();
                deleteDe(temp.getData().getIdentification());
                addToStartDe(boy);
            }
            temp = temp.getNext();
        }
    }

    /**
     * Método que dada una edad y un municipio me permita obtener el listado de los niños pertenecientes a ese municipio y de edad menor o igual a la dada
     * @param age
     * @param description
     * @throws ListaDeException
     */
    public void listBoysByLocationByAge(byte age, String description) throws ListaDeException {
        /**
         * inicio una nueva lista para guardar los datos que necesito
         */
        validateListEmptyDe();
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * si el ayudante con el dato y la edad compara que la edad es menor o igual
             * a la edad del niño
             */
            if (temp.getData().getAge() > age ||!temp.getData().getLocation().getDescription().equals(description)) {
                deleteDe(temp.getData().getIdentification());
            }
            /**
             * el ayudante pasara al siguiente
             */
            temp = temp.getNext();
        }
    }
    public  void  deleteBypositionDe(int position)throws ListaDeException{
        validateListEmptyDe();
        //validacion de posicion
        if (position > count){
            throw new ListaDeException("No hay datos en la posicion ingresada");
        }
        if (position == 1){
            deleteDe(head.getData().getIdentification());
        }
        else {
            int cont = 1;
            Node temp = this.head;
            while (temp != null){
                if (cont == position -1){
                    break;
                }
                temp = temp.getNext();
                cont++;
            }
            deleteDe(temp.getNext().getData().getIdentification());
        }
    }

    public GendersByGradeDTO getGendersByGradeByLocation(byte grade, Location location) throws ListaDeException {
        validateListEmptyDe();
        Node temp = this.head;
        int countTotal = 0;
        int countM = 0;
        int countF = 0;
        while (temp != null) {
            if (temp.getData().getLocation().getCode().equals(location.getCode())
                    && temp.getData().getGrade() == grade){
                countTotal++;
                if (temp.getData().isOrphans()){
                    if (temp.getData().getGender().equals(MASCULINO)){
                        countM++;
                    }
                    else {
                        countF++;
                    }
                }
            }
            temp = temp.getNext();
        }
      List<CountByGenderDTO> countByGenderDTOS = new ArrayList<>();
        countByGenderDTOS.add(new CountByGenderDTO(Gender.MASCULINO, countM));
        countByGenderDTOS.add(new CountByGenderDTO(Gender.FEMENINO, countF));
        GendersByGradeDTO gendersByGradeDTO = new GendersByGradeDTO(grade,countByGenderDTOS, countTotal);
        return gendersByGradeDTO;
    }
    public GradesByLocationDTO gradesByLocationDTO(Location location)throws ListaDeException {
        List<GendersByGradeDTO> gendersByGradeDTOS = new ArrayList<>();
        for (byte i = 1; 1 < 5; i++) {
            gendersByGradeDTOS.add(getGendersByGradeByLocation(i, location));
            {
                GradesByLocationDTO gradesByLocationDTO = new GradesByLocationDTO(location, gendersByGradeDTOS);
                return gradesByLocationDTO;
            }
        }
    }

    /**
     * Metodo por el cual retornara una lista con los niños de primero y las niñas de segundo invirtiendo la lista de los niños
     * @throws ListaDeException
     */
    public void orderListChange() throws ListaDeException {
        /**
         * valido si la lista esta vacia
         */
        validateListEmptyDe();
        /**
         * creo una nuneva lista temporal
         */
        ListaDE listTemp = new ListaDE();
        /**
         * creo el ayudante y lo coloco en la cabeza
         */
        Node temp = this.head;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * el ayudante con el dato que tiene valida el genero si es igual a masculino
             * lo lleva a la nueva lista y lo adiciona a la cabeza
             */
            if (temp.getData().getGender().equals(MASCULINO)) {
                listTemp.addToStartDe(temp.getData());
                /**
                 * si no
                 */
            } else {
                /**
                 * en la lista temporal envie el dato del otro niño que no sea masculino
                 */
                listTemp.addDe(temp.getData());
            }
            /**
             * el ayudante pasara al siguiente
             */
            temp = temp.getNext();
        }
        /**
         * en la cabeza coloque la lista temporal que se creo
         */
        this.head = listTemp.head;
    }

}
