package com.umanizales.lists_prog2.model.listase;
/**
 * Clase que me permite gestionar una lista simplemente enlazada
 * contiene los métodos u operaciones
 * solo cuenta con el atributo head = que representa el primer niño
 * tiene un contador para saber cuantos niños hay en las listas
 */

import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.Gender;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    /**
     * Atributo que represenata el inicio de la lista
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
     * @throws ListaSeException metodo para expresar el mensaje o exepcion
     */

    //Añadir niño
    public void add(Boy boy) throws ListaSeException {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si el niño ya existe se envia un mensaje al usuario
             * para que sepa que ya existe la identificaion ingresada
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * si la cabeza esta vacia
         */
        if (this.head == null) {
            /**
             *la cabeza tomara al nuevo niño que se esta ingresando y sera la cabeza
             */
            this.head = new Node(boy);
        } else {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * Creo un ccilo para recorrer la lista SE de principio a fin
             * llego al final cuando mi ayudante queda para en vacío (null)
             */
            while (temp.getNext() != null) {
                /**
                 * Pregunto si el niño en el cual está ubicado mi ayudante es el de la identificación
                 * que estoy buscando ingresado en el parámetro identificacion
                 */
                if (boy.getIdentification().equals(temp.getData().getIdentification())) {
                    /**
                     * lanza el mensaje al usuario que la identificaion del niño que estan ingresando ya esta
                     */
                    throw new ListaSeException("El niño con esta identificación ya está agregado");
                }
                /**
                 * el ayudante se pasa al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * el ayudante coloca al nuevo niño en el brazo del niño sobre el que esta parado
             */
            temp.setNext(new Node(boy));
        }
        /**
         * llevamos un niño al contador para saber cuantos niños existen
         */
        count++;
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
     * metodo para invertir la loista y que el ultimo quede en la cabeza o inicio
     *
     * @throws ListaSeException
     */
    public void invert() throws ListaSeException {
        /**
         * preguntamos si en la cabeza hay algo y no esta vacia iniciamos el ciclo
         */
        if (this.head != null) {
            /**
             * creamos una nueva lista temporal en la cual colocaremos los nodos del final al inicio
             */
            ListSE listTemp = new ListSE();
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
     * Método que me busca en la lista simplemente enlazada, un niño a partir de la identificación
     * Si no encuentra el niño retorna vacío (null)
     * @param identification Cédula, TI, CE , Sisben que identifica el niño que voy a buscar
     * @return El niño que encontré con todos sus datos
     */
    public Boy findByIdentification(String identification) {//*
        /**
         * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp= this.head;
        /**
         * Creo un ccilo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda para en vacío (null)
         */
        while(temp!=null)
        {
            /**
             * Pregunto si el niño en el cual está ubicado mi ayudante es el de la identificación
             * que estoy buscando ingresado en el parámetro identificacion
             */
            if(temp.getData().getIdentification().equals(identification))
            {
                /**
                 * Lo encontré y lo retorno de inmediato
                 * Finaliza mi método
                 */
                return temp.getData();
            }
            /**
             * Mi ayudante se pasa al siguiente nodo
             */
            temp=temp.getNext();
        }
        /**
         * Si llega a esta línea significa que no encontré el niño y retorno vacío
         */
        return null;
    }

    /**
     * metodo por el cual validaremos que la lista este vacia
     *
     * @throws ListaSeException
     */
    public void validateListEmpty() throws ListaSeException {
        /**
         * si en la cabeza o inicio de la lista es igual a vacio (null)
         */
        if (this.head == null) {
            /**
             * generamos el mensaje de exepcion para indicarle al usuario que la lista esta vacia
             */
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * metodo por el cual agregamos un nuedo nodo o niño a la lista en el inicio de la lista
     * @param boy
     * @throws ListaSeException
     */
    public void addToStart(Boy boy) throws ListaSeException {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * si la cabeza esta vacia
         */
        if (this.head == null) {
            /**
             * el nuevo dato que se esta ingresando sera la cabeza o inicio de la lista
             */
            this.head = new Node(boy);
        } else {
            /**
             * el ayudante tomara al nuevo nodo y ara que el nuevo nodo tome a la cabeza por lo cual el nuevo nodo sera la cabeza
             */
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head = temp;
        }
        /**
         * aumenta el contador en uno para tener el dato de cuantos niños existen
         */
        count++;
    }

    /**
     * metodo por el caul agregaremos un nodo en una posicion dada
     *
     * @param boy
     * @param position
     * @throws ListaSeException
     */
    public void addByPosition(Boy boy, int position) throws ListaSeException {
        /**
         * Se invoca al metodo encontrar por identificacion, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * generamos el mensaje donde le indicamos al usuario que el niño con esa identificacion ya existe
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /// Validación de la posición
        /**
         * validamos la posicion verificando que si este esa posicion en la lista
         */
        if (position > count) {
            /**
             * generamos un mensaje de alerta indicandole al usuario que la posicion en la cual quiere
             * adicionar el nodo no existe
             */
            throw new ListaSeException("La posición ingresada no es valida");
        }
        /**
         * si la posicion es la primera metermoes el nodo en la cabeza de la lista
         */
        if (position == 1) {
            addToStart(boy);
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
                if (cont == position - 1) {
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
             * en el brazo siguiente
             */
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
            count++;
        }
    }

    /**
     * metodo por el cual eliminaremos un nodo solicitado validando la identificacion sea igual a la solicitada
     *
     * @param identification
     * @throws ListaSeException
     */
    public void delete(String identification) throws ListaSeException {
        /**
         * si la cabeza esta vacia
         */
        if (this.head != null) {
            /**
             * si en la cabeza esta el dato con la identificacion y es igual a la identificacion que se debe eliminar
             */
            if (this.head.getData().getIdentification().equals(identification)) {
                /**
                 * la cabeza sera igual a lo que tiene la cabeza en su siguiente
                 */
                this.head = this.head.getNext();
                /**
                 * al contador le restamos uno para siempre tener exacto los datos de la lista
                 */
                count--;
            } else {
                /**
                 * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
                 * LLamo a un ayudante y lo ubico en la cabeza o inicio
                 */
                Node temp = this.head;
                while (temp != null) {
                    /**
                     * si en el siguiente no esta vacio y el siguiente tenga el dato y la identificacion sea
                     * igual a la identificacion hacemos que el ayudante tome al siguiente
                     */
                    if (temp.getNext() != null && temp.getNext().getData().getIdentification().equals(identification)) {
                        break;
                    }
                    temp = temp.getNext();
                }
                /**
                 * temp va a estar parado en el anteror al que debe eliminar o va a ser null
                 */
                if (temp == null) {
                    /**
                     * al contadore le quitaremos uno y el ayudante tomara al siguiente del siguiente
                     */
                    count--;
                    temp.setNext(temp.getNext().getNext());
                } else {
                    /**
                     * generamos un mensaje donde le indicaremos al usario que la identificacion no existe
                     */
                    throw new ListaSeException("La identificación " + identification + " no existe en la lista");
                }
            }
        } else {
            /**
             * generamos un mensaje al usuario donde le dira que no hay datos en la lista
             */
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * metodo por el cual listamos los niños sin estar agarrados de la mano
     *
     * @return
     * @throws ListaSeException
     */
    public List<Boy> list() throws ListaSeException {
        /**
         * preguntamos si en la cabeza hay algo y no esta vacia iniciamos el ciclo
         */
        if (this.head != null) {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * tendremos una nueva lista que estara vacia a la cual llevaremos los niños uno a uno en orden
             * pero sin estar agarrados mientras el ayudante no llegue a un siguiente vacio
             */
            List<Boy> list = new ArrayList<>();
            while (temp != null) {
                /**
                 * el ayudante llevara los niños a la lista nueva y los agragara
                 */
                list.add(temp.getData());
                /**
                 * el ayudante pasara al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * termino el ciclo y retorno la lista de niños sin estar agarrados uno del otro
             */
            return list;
        }
        /**
         * generamos un mensaje al usuario informandole que la lista esta vacia
         */
        throw new ListaSeException("No hay datos en la lista");
        //return null;
    }

    /**
     * metodo por el cual generaremos un cambio de extremos en la lista
     *
     * @throws ListaSeException
     */
    public void changeXtremes() throws ListaSeException {
        /**
         * para un cambio de extremos debemos validar que almenos existan dos datos
         * entonces validamos que la cabeza no este vacia y que el siguiente a la cabeza no este vacio
         */
        if (this.head != null && this.head.getNext() != null) {
            /**
             * sacar el niño de la cabeza
             */
            Boy start = this.head.getData();
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = head;
            while (temp.getNext() != null) ;
            {
                /**
                 * decimos al ayudante pase al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * temporal esta ubicado en el ultimo niño y dejara alli al niño de la cabeza
             */
            this.head.setData(temp.getData());
            temp.setData(start);
        } else {
            /**
             * generamos un mensaje al usuario informandole que no es posible realizar el cambio
             */
            throw new ListaSeException("No es posible ejecutar el cambio de extremos");
        }
    }

    /**
     * metodo por el cual lisatamos niños por genero
     *
     * @param gender
     * @return
     * @throws ListaSeException
     */
    public ListSE getListSeBoysByGender(String gender) throws ListaSeException {
        /**
         * llamamos al metodo que creamos anteriormente que nos valida si la lista no esta vacia
         */
        validateListEmpty();
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * creamos una nueva lista que sera temporal
         */
        ListSE listTemp = new ListSE();
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * si los datos del genero son iguales al genero que necesitamos
             */
            if (temp.getData().getGender().equals(gender)) {
                /**
                 * adicionamos a la nueva lista el genero que encontamos
                 */
                listTemp.add(temp.getData());
            }
            /**
             * el ayudante pasara al siguiente nodo
             */
            temp = temp.getNext();
        }
        /**
         * terminamos el cilco y retornemos la lista temporal con los datos del genero del niño o niña
         */
        return listTemp;
    }

    /**
     * metodo por el cual hacemos una lista por genero
     *
     * @throws ListaSeException
     */
    public void variantBoys() throws ListaSeException {
        /**
         * emvocamos al metodo que creamos anteriormente para saber si la lista esta vacia
         */
        validateListEmpty();
        /**
         * creamos una lista de niños
         */
        ListSE kids = this.getListSeBoysByGender("MASCULINO");
        /**
         * creamos una lista de niñas
         */
        ListSE girls = this.getListSeBoysByGender("FEMENINO");
        /**
         * creamos una lista maxima y una minima y la iniciamos vacias
         */
        ListSE minList = null;
        ListSE maxList = null;
        /**
         * contamos los niños y si son mas cantidad que las niñas
         */
        if (kids.getCount() > girls.getCount()) {
            /**
             * en la lista minima colocamos las niñas y en la maxima a los niños
             */
            minList = girls;
            maxList = kids;
        } else {
            /**
             * pero si al contar las listas las niñas tienen mayoria en la lista minima quedaran los niños y en la maxima las niñas
             */
            minList = kids;
            maxList = girls;
        }
        /**
         * llamamos al ayudante y lo colocamos en la cabeza de la lista minima y le decimos que vaya de 2 en 2 recorriendo la lista
         */
        Node temp = minList.getHead();
        int pos = 2;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * en la lista maxima en la posicion 2 agregue el dato de la lista minima y valla asi de 2 en 2
             */
            maxList.addByPosition(temp.getData(), pos);
            pos = pos + 2;
            temp = temp.getNext();
        }
        /**
         * la cabeza de la lista sera la cabeza de la lista maxima
         */
        this.head = maxList.getHead();
    }

    /**
     * Metodo que recibe el codigo de una ciudad y retorna la cantidad de niños que hay en esa ciudad
     *
     * @param code
     * @return
     */
    public int getCountBoysByLocation(String code) {
        /**
         * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.getHead();
        /**
         * iniciamos el contador en cero y mientras que el siguiente del ayudante no este vacio
         */
        int count = 0;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null) {
            /**
             * validamos si el dato que tiene el ayudante con la locacion y el codigo es igual al codigo ingresado
             */
            if (temp.getData().getLocation().getCode().equals(code))
            {
                /**
                 * aumentamos el contador en uno
                 */
                count++;
            }
            /**
             * el ayudante pasara al siguiente
             */
            temp = temp.getNext();
        }
        /**
         * termina el metodo y retornamos el contador con la cantidad de niños encontrados por la locacion dada
         */
        return count;
    }

    /**
     * metodo para encontrar un niño por locacion
     * @param code
     * @return
     */
    public Boy findByLocation (String code)
    {
        /**
         * Cómo no me puedo mover de la cabeza por que se me vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * Le indicamos al ayudante  que recorra la lista hasta que este vacia
         */
        while (temp != null)
        {
            /**
             * el ayudante con los datos de la locacion y el codigo es igual al codigo que estoy buscando
             * retorne el dato
             */
            if (temp.getData().getLocation().getCode().equals(code))
            {
                return temp.getData();
            }
            /**
             * el ayudante pasara al siguiente
             */
            temp = temp.getNext();
        }
        /**
         * termina el metodo y retornamos vacio
         */
        return null;
    }

     /**
     * metodo que dada una edad y un municipio permita obtener el listado pertenecientes al municipio
     * y la edad menor o igual a la dada y liste los niños
      * @return
      */
    public List<Boy> listBoysByLocationByAge(byte age, String location) {
        if (this.head != null)
        {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.getHead();
            /**
             * inicio una nueva lista para guardar los datos que necesito
             */
            List<Boy> list = new ArrayList<>();
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                /**
                 * si el ayudante con el dato y la edad compara que la edad es menor o igual
                 * a la edad del niño
                 */
                if (temp.getData().getAge() <= age && temp.getData().getLocation().getDescription().equals(location))
                    {
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
            /**
             * retorno la lista con los datos solicitados
             */
            return list;
      }
        /**
         * termino el ciclo y retorno vacio
         */
        return null;
    }

    /**
     *
     Método que, dado un género y una edad, me deje al inicio de la lista los niños del género dado y con la edad
     menor o igual a la entregada
     * @param age
     *
     */
    public void listByGenderAge(byte age, String gender) throws ListaSeException {
        if (this.head != null) {
            /**
             * Cómo no me puedo mover de la cabeza porque se me vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * creo una nueva lista donde iran los niños encontrados con el genero y la edad requerida
             */
            ListSE list = new ListSE();
            /**
             * Le indicamos al ayudante  que recorra la lista hasta que este vacia
             */
            while (temp != null) {
                /**
                 * si el dato que tiene el ayudante de genero es igual al genero y el dato de la edad es menoro
                 * igual a la edad del niño
                 */
                if (temp.getData().getGender().equals(gender) && temp.getData().getAge()
                        <= age) {
                    /**
                     * los listo al inicio con el dato
                     */
                    list.addToStart(temp.getData());
                } else {
                    list.add(temp.getData());
                }
                /**
                 * el ayudante pasara al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * la cabeza sera la nueva lista
             */
            this.head = list.getHead();
        }
        /**
         * envio un mensaje al usuario informando que no hay niños por la edad y del genero para listar
         */
        throw new ListaSeException("No hay niños"+age + gender+"para listar");
    }

/**
 * Método que me permita eliminar los niños con una edad mayor a la suministrada
 */
     public void deleteByAge ( byte age)throws ListaSeException{
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
           /**
           * eliminamos el dato y la identificacion para eliminar el niño
           */
               delete(temp.getData().getIdentification());
           }
           /**
           * ayudante pasa al siguiente
           */
              temp = temp.getNext();
           }
           /**
            * envio un mensaje al usuario informando que la lista esta vacia
            */
            throw new ListaSeException("la lista esta vacia");
        }
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
     * Método que me permita retirar a todos los niños de un género de la lista
     * @param code
     * @throws ListaSeException
     */
    public void deleteByGender (String code)throws ListaSeException
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
                }
                /**
                 * ayudante pasa al siguiente
                 */
                temp = temp.getNext();
           }
        /**
         * envio un mensaje al usuario informando que la lista esta vacia
         */
        throw new ListaSeException("la lista esta vacia");
        }

    /**
     * Método que me permita obtener los niños de un grado de escuela dado
     * @param degree  grado de la escuela del niño 1 al 5
     * @return retorna la lista de los niños segun el grado indicado
     */
    public List<Boy> listBoysDegree(Integer degree) {
        /**
         * si la cabeza es diferente a vacio llamo al ayudante para empezar a recorrer la lista
         */
        if (this.head != null) {
            Node temp = this.head;
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
     * Método que me permita eliminar un niño dada una posición
     * @param boy
     * @param position
     * @throws ListaSeException
     */
    public void deleteByPosition(Boy boy, int position) throws ListaSeException
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
        if(position == 1)
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

}