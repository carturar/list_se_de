package com.umanizales.lists_prog2.model.listade;
/**
 * Clase que me permite gestionar una lista Doblemente enlazada
 * contiene los métodos u operaciones
 * solo cuenta con el atributo head = que representa el primer niño
 * tiene un contador para saber cuantos niños hay en las listas
 * @author Carlos Arias
 */
import com.umanizales.lists_prog2.exception.ListaDeException;


import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.listade.ListaDE;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;


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
     * @param boy es el parametro que estamos usando
     * @throws ListaDeException metodo para expresar el mensaje o exepcion
     */
    public void add(Boy boy) throws ListaDeException
    {
        /**
         * si la cabeza esta vacia
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if(boyExist !=null) {
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
             Node newBoy = new Node(boy);
             temp.setNext(new Node(boy));
             newBoy.setPrevious(temp);
       }
       count ++;
  }
    /**
     * Método que me busca en la lista simplemente enlazada, un niño a partir de la identificación
     * Si no encuentra el niño retorna vacío (null)
     * @param identification Cédula, TI, CE , Sisben que identifica el niño que voy a buscar
     * @return El niño que encontré con todos sus datos
     */
    public Boy findByIdentification(String identification) {
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
     * @throws ListaDeException
     */
    public void validateListEmpty() throws ListaDeException
    {
        /**
         * si en la cabeza o inicio de la lista es igual a vacio (null)
         */
        if (this.head == null)
        {
            /**
             * generamos el mensaje de exepcion para indicarle al usuario que la lista esta vacia
             */
            throw new ListaDeException("No hay datos en la lista");
        }
    }
    /**
     * creamos una variable llamada contador el cual contendra nuemeros enteros para guardar la cantidad de niños
     */
    public int count() {
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
     * @param boy
     * @throws ListaDeException
     */
    public void addToStart (Boy boy) throws ListaDeException
    {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist !=null)
        {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        /**
         * si la cabeza esta vacia
         */
        if (this.head == null)
        {
            /**
             * el nuevo dato que se esta ingresando sera la cabeza o inicio de la lista
             */
            this.head = new Node(boy);
            this.head.setPrevious(null);
        }
        else
        {
            /**
             * el ayudante tomara al nuevo nodo y ara que el nuevo nodo tome a la cabeza
             * por lo cual el nuevo nodo sera la cabeza
             */
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head = temp;
            this.head.setPrevious(null);
        }
        count++;
    }

    /**
     * metodo por el caul agregaremos un nodo en una posicion dada
     * @param boy
     * @param positon
     * @throws ListaDeException
     */
    public void addByPosition(Boy boy, int positon) throws ListaDeException
    {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null)
        {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaDeException("La identificacion ingresada ya existe");
        }
        /**
         * validamos la posicion verificando que si este esa posicion en la lista
         */
        if (positon > count)
        {
            /**
             * generamos un mensaje de alerta indicandole al usuario que la posicion en la cual quiere
             * adicionar el nodo no existe
             */
            throw new ListaDeException("La posición ingresada no es válida");
        }
        /**
         * si la posicion es la primera metermoes el nodo en la cabeza de la lista
         */
        if (positon ==1)
        {
            addToStart(boy);
        }
        else
        {
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
            while (temp != null)
            {
                /**
                 * si se valida que el contador queda en una posicion negativa se rompe el ciclo
                 */
                if (cont == positon -1)
                {
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
            temp.setNext(this.head);
            this.head = temp;
            this.head.setPrevious(null);
            count++;
        }
    }
    public void invert() throws ListaDeException {
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
                listTemp.addToStart(temp.getData());
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
     * @param boy
     * @param position
     * @throws ListaDeException
     */
    public void deleteByPosition(Boy boy, int position) throws ListaDeException
    {
        /**
         * Validación de la posicíon, si la posición que se ingresó es mayor a mi contador
         */
        if(position > count)
        {
            this.add(boy);
            return;
            //throw  new ListaSeException("La posición ingresada no es válida");
        }
        /**
         * en caso de que sea la posición 1, es decir la cabeza
         */
        if(position == 1) // en caso de que sea la posición 1, es decir la cabeza
        {
            this.head = null;
        }
        else
        {
            /**
             * inicio mi contador en 1 porque si entró acá quiere decir que por lo menos hay uno
             */
            int cont=1;
            /**
             * Creo mi ayudante y lo paro en la cabeza
             */
            Node temp = this.head;
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while(temp!=null)
            {
                if(count == position-1 )
                {
                    /**
                     * pongo a mi ayudante a apuntar al siguiente de su siguiente
                     */
                    temp= temp.getNext().getNext();
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
                    count++;}

            }

        }
    }
    /**
     * Método creado para listar los niños de un género que me ingresan
     * @param gender entra como parámetro para listar los niños del género
     * @return // retorna el listado de niños que pertenecen a ese género
     * @throws ListaDeException
     */
    public ListaDE getListDeBoysByGender(String gender) throws ListaDeException
    {
        validateListEmpty();
        Node temp= this.head;
        ListaDE listTemp = new ListaDE();
        while(temp !=null)
        {
            if(temp.getData().getGender().getCode().equals(gender))

            {
                listTemp.add(temp.getData());
            }
            temp = temp.getNext();
        }
        return listTemp;
    }
    public void variantBoys() throws ListaDeException
    {
        validateListEmpty(); // valido que en mi lista existan datos
        ListaDE kids= this.getListDeBoysByGender("MASCULINO");
        ListaDE girls= this.getListDeBoysByGender("FEMENINO");
        ListaDE minList= null;
        ListaDE maxList= null;
        if(kids.getCount()> girls.getCount())
        {
            minList= girls;
            maxList = kids;
        }
        else
        {
            minList = kids;
            maxList = girls;
        }
        Node temp= minList.getHead();
        int pos=2;
        while(temp != null)
        {
            maxList.addByPosition(temp.getData(), pos);
            pos = pos +2;
            temp = temp.getNext();
        }
        this.head= maxList.getHead();
    }
    public void deleteByGender (String code)throws ListaDeException
    {
        /**
         * llamamos el metodo par validar si la lista esta vacia
         */
        validateListEmpty();
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
            if(temp.getData().getGender().getCode().equals(code));
            {
                /**
                 * eliminamos el dato con el genero para eliminar el niño
                 */
                delete(temp.getData().getIdentification());
                temp =temp.getNext().getNext();
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
    public void delete(String identification) throws ListaDeException {
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
    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños
     * @param code entra como parámetro con el código que identifica la ciudad
     * @return me retorna el contador con la cantidad de datos obtenidos en el método
     **/
    public int getCountBoysByLocation(String code)
    {
        Node temp= this.getHead();
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
    public void changeXtremes() throws  ListaDeException{
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
    public List<Boy> listBoysDegree(Integer degree) {
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
                if (temp.getData().getDegree().getDegree().equals(degree)) {
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
     * @param code
     * @return
     */
    public int getCountBoysByGender (String code)
    {
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio adicional llevo un contador
         */
       Node temp = this.getHead();
        temp.getNext().setPrevious(null);
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
            if(temp.getData().getGender().getCode().equals(code));
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

    public void deleteByAge (byte age)throws ListaDeException {

        /**
         * si la cabeza es diferente a vacio llamo al ayudante para empezar a recorrer la lista
         */
        if (this.head != null) {
            /**
             * paramos al ayudante en la cabeza
             */
            Node temp = this.head;
            temp.getNext().setPrevious(null);
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                /**
                 * validamos si el ayudante con el dato y la edad que trae es menor a la edad del niño
                 */
                if (temp.getData().getAge() > age);
                {
                    /**
                     * eliminamos el dato y la identificacion para eliminar el niño
                     */
                    temp= temp.getNext().getNext();
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
    }
    /**
     Método dado un género y edad, me entrega en una lista al inicio  los niños del género
     con la edad menor o igual a la entregada
     * @param age la edad que ingresan para el retorno
     *
     */
    public void listByGenderAge(byte age, String gender) throws ListaDeException{
        if (this.head != null) {
            Node temp = this.head;
            ListaDE list = new ListaDE();
            while (temp != null) {
                if ((temp.getData().getGender().equals(gender))
                        && (temp.getData().getAge() <= age)) {
                    list.addToStart(temp.getData());
                } else {
                    list.add(temp.getData());
                    temp.getNext().setPrevious(temp);
                }
                temp = temp.getNext();
            }
            this.head = list.getHead();
        }
        throw new ListaDeException("No hay niños"+age + gender+"para listar");
    }
    public List<Boy> listBoysByLocationByAge(byte age, String location) {
        /**
         * inicio una nueva lista para guardar los datos que necesito
         */
        List<Boy> list = new ArrayList<>();
        if (this.head != null) {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.getHead();
            temp.getNext().setPrevious(null);
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                /**
                 * si el ayudante con el dato y la edad compara que la edad es menor o igual
                 * a la edad del niño
                 */
                if (temp.getData().getAge() <= age && temp.getData().getLocation().getDescription().equals(location)) {
                    /**
                     * agrega el niño con los datos a la nueva lista
                     */
                    list.add(temp.getData());
                }
                /**
                 * el ayudante pasara al siguiente
                 */
                temp = temp.getNext();
            }
        }
        /**
         * retorno la lista con los datos solicitados
         */
        return list;
    }
}
