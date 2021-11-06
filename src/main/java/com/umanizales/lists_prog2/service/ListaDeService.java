package com.umanizales.lists_prog2.service;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.*;
import com.umanizales.lists_prog2.model.listade.ListaDE;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaDeService {
    private ListaDE listBoys;
    private List<Location> locations;
    private List<Gender1> gender;

    public ListaDeService(){
        listBoys = new ListaDE();
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
    public ResponseEntity<ResponseDTO> addBoy(Boy boy)throws ListaDeException
    {
        if (!validateLocation(boy.getLocation()))
        {
            throw new ListaDeException("La ubicacion ingresada no es valida");
        }
        listBoys.add(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaDeException
    {
        listBoys.addByPosition(boy, position);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyToStart(Boy boy)throws ListaDeException
    {
        listBoys.addToStart(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoys() throws ListaDeException
    {
        if(listBoys.getHead() == null)
        {
            throw new ListaDeException("No hay datos en la lista");
        }
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getHead(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> invertList()throws ListaDeException
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

    public ResponseEntity<ResponseDTO> changeXtremes()throws ListaDeException
    {
        listBoys.changeXtremes();
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", true, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> delete(String identification)throws ListaDeException
    {
        listBoys.delete(identification);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado", identification, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> variantList() throws ListaDeException
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
    public  ResponseEntity<ResponseDTO> byGenderAge(byte age, String gender)throws ListaDeException{
        listBoys.listByGenderAge(age, gender);
        return new ResponseEntity<>(
                new ResponseDTO("satisfactorio",true, null), HttpStatus.OK);
    }
    public  ResponseEntity<ResponseDTO>deleteByAge(byte age)throws ListaDeException{
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
    public ResponseEntity<ResponseDTO>deleteByGender(String code) throws ListaDeException {
        listBoys.deleteByGender(code);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado", true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>listBoysDegree (Integer degree){
        listBoys.listBoysDegree(degree);
        return new ResponseEntity<>(new ResponseDTO("satisfactorio", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>deleteByPosition(Boy boy,int position)throws ListaDeException {
        listBoys.deleteByPosition(boy,position);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado",true,null), HttpStatus.OK);
    }
}
