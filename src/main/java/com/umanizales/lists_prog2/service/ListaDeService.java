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
       // gender=new ArrayList<>();
      //  gender.add(new Gender.MASCULINO);
       // gender.add(new Gender.MASCULINO);
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
        listBoys.addDe(boy);
        return new ResponseEntity<>(new ResponseDTO("Niño adicionado", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaDeException
    {
        listBoys.addByPositionDe(boy, position);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyToStart(Boy boy)throws ListaDeException
    {
        listBoys.addToStartDe(boy);
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
        listBoys.invertDe();
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getHead(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getCount()
    {
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.getCount(), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> count()
    {
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", listBoys.countDe(), null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> changeXtremes()throws ListaDeException
    {
        listBoys.changeXtremesDe();
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", true, null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> delete(String identification)throws ListaDeException
    {
        listBoys.deleteDe(identification);
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
            int count = listBoys.getCountBoysByLocationDe(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc,count));
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", boysByLocations,null)
                ,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> boysByLocationByAgeDe(byte age, String descriptiom) throws ListaDeException{
        listBoys.listBoysByLocationByAge(age, descriptiom);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", true,null)
                ,HttpStatus.OK);
    }
    public  ResponseEntity<ResponseDTO> byGenderAgeDe(Gender gender, byte age)throws ListaDeException{
        listBoys.listByGenderAgeDe(gender, age);
        return new ResponseEntity<>(
                new ResponseDTO("satisfactorio",true, null), HttpStatus.OK);
    }
    public  ResponseEntity<ResponseDTO>deleteByAgeOlder(byte age)throws ListaDeException{
        listBoys.deleteByAgeOlderDe(age);
        return  new ResponseEntity<>(new ResponseDTO("Niño eliminado", deleteByAgeOlder(age), null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>getCountBoysByGender(Gender gender){
        List<BoysByGender> boysByGenders = new ArrayList<>();

        int count = listBoys.getCountBoysByGenderDe(Gender.FEMENINO);
        boysByGenders.add(new BoysByGender(Gender.FEMENINO, count));

        int count2 = listBoys.getCountBoysByGenderDe(Gender.MASCULINO);
        boysByGenders.add(new BoysByGender(Gender.MASCULINO, count2));

        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", boysByGenders,null)
                ,HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>deleteByGender(Gender gender) throws ListaDeException {
        listBoys.deleteByGenderDe(gender);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado", true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>listBoysGrade (byte grade){
        listBoys.listBoysGradeDe(grade);
        return new ResponseEntity<>(new ResponseDTO("satisfactorio", true, null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO>deleteByPosition(Boy boy,int position)throws ListaDeException {
        listBoys.deleteByPositionDe(boy,position);
        return new ResponseEntity<>(new ResponseDTO("niño eliminado",true,null), HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getOrphanByGradesByLocation(){
        List<GradesByLocationDTO> gradesByLocationDTOS = new ArrayList<>();
        //Recorrer todas las location parado parado en una location el metodo de la lista
        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", gradesByLocationDTOS, null),HttpStatus.OK);
    }

}
