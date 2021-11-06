package com.umanizales.lists_prog2.service;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.*;
import com.umanizales.lists_prog2.model.listase.ListSE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSeService {
    private ListSE listBoys;
    private List<Location> locations;
    private List<Gender1> gender;

    public ListSeService(){
        listBoys = new ListSE();
        initializaLocations();
        initializationGender();
    }
    private void  initializaLocations()
    {
        locations = new ArrayList<>();
        locations.add(new Location("16917001", "Manizales"));
        locations.add(new Location("16917003", "Chinchina"));
    }

    public void initializationGender()
    {
        gender=new ArrayList<>();
        gender.add(new Gender1("1","Masculino"));
        gender.add(new Gender1("2","Femenino"));
    }

    public boolean validateLocation(Location location)
    {
        for (Location loc: locations)
        {
            if (loc.getCode().equals(location.getCode()) && loc.getDescription().equals(location.getDescription()))
            {
                return  true;
            }
        }
            return  false;
    }
    public ResponseEntity<ResponseDTO> addBoy(Boy boy)throws ListaSeException
    {
        if (!validateLocation(boy.getLocation()))
        {
            throw new ListaSeException("La ubicacion ingresada no es valida");
        }
        listBoys.add(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaSeException
    {
        listBoys.addByPosition(boy, position);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyToStart(Boy boy)throws ListaSeException
    {
        listBoys.addToStart(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoys() throws ListaSeException
    {
        if(listBoys.getHead() == null)
        {
            throw new ListaSeException("No hay datos en la lista");
        }
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getHead(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> invertList()throws ListaSeException
    {
        listBoys.invert();
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getHead(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getCount()
    {
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getCount(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> count()
    {
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.count(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> listBoysFree() throws ListaSeException
    {
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.list(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> changeXtremes()throws ListaSeException
    {
        listBoys.changeXtremes();
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", true, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> delete(String identification)throws ListaSeException
    {
        listBoys.delete(identification);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado", identification, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> Sex(String sex) throws ListaSeException
    {
        return new ResponseEntity<>(new ResponseDTO("Lista Genero", listBoys.list().stream().filter
                (boy -> boy.getGender().equals(sex)),null),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> variantList() throws ListaSeException
    {
        listBoys.variantBoys();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead(),null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getBoysByLocation()
    {
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for (Location loc: locations)
        {
            int count = listBoys.getCountBoysByLocation(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc,count));
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", boysByLocations,null)
                ,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> boysByLocationByAge(byte age, String location) {
        listBoys.listBoysByLocationByAge(age, location);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.listBoysByLocationByAge(age, location),null)
                ,HttpStatus.OK);
    }
    public  ResponseEntity<ResponseDTO> byGenderAge(byte age, String gender)throws ListaSeException{
        listBoys.listByGenderAge(age, gender);
        return new ResponseEntity<>(
                new ResponseDTO("satisfactorio",true, null), HttpStatus.OK);
    }
    public  ResponseEntity<ResponseDTO>deleteByAge(byte age)throws ListaSeException{
        listBoys.deleteByAge(age);
        return  new ResponseEntity<>(new ResponseDTO("Niño eliminado", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>getCountBoysByGender(){
        List<BoysByGender> boysByGenders = new ArrayList<>();
        for (Gender1 gend: gender)
        {
            int count = listBoys.getCountBoysByGender(gend.getCode());
            boysByGenders.add(new BoysByGender(gend,count));
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", boysByGenders,null)
                ,HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>deleteByGender(String code) throws ListaSeException {
        listBoys.deleteByGender(code);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado", true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>listBoysDegree (Integer degree){
       listBoys.listBoysDegree(degree);
       return new ResponseEntity<>(new ResponseDTO("satisfactorio", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>deleteByPosition(Boy boy,int position)throws ListaSeException {
       listBoys.deleteByPosition(boy,position);
       return new ResponseEntity<>(new ResponseDTO("niño eliminado",true,null), HttpStatus.OK);
    }
}


