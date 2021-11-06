package com.umanizales.lists_prog2.controller;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.service.ListaDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "boysde")
public class BoysControllerDe {

    @Autowired
    private ListaDeService listsDeService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addBoy(@RequestBody @Valid Boy boy) throws ListaDeException
    {return listsDeService.addBoy(boy);}

    @PostMapping(path = "addtostart")
    public ResponseEntity<ResponseDTO>addBoyToStart(@RequestBody Boy boy)throws ListaDeException
    {return listsDeService.addBoyToStart(boy);}

    @PostMapping(path = "addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Boy boy)throws ListaDeException
    {return listsDeService.addBoyByPosition(boy,position);}

    @GetMapping
    public ResponseEntity<ResponseDTO> listBoys() throws ListaDeException
    {return listsDeService.listBoys();}

    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDTO> invertList()throws ListaDeException
    {return listsDeService.invertList();}

    @PostMapping(path = "addboys")
    public ResponseEntity<ResponseDTO> addBoys(@RequestBody List<Boy> boys)throws ListaDeException
    {for (Boy boy:boys)
    {listsDeService.addBoy(boy);}
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Satisfactorio",
                listsDeService.listBoys(),null), HttpStatus.OK);}

    @GetMapping(path = "getcount")
    public ResponseEntity<ResponseDTO> getCount()
    {return listsDeService.getCount();}

    @GetMapping(path = "count")
    public ResponseEntity<ResponseDTO> count()
    {return listsDeService.count();}

    @GetMapping(path = "changextremes")
    public ResponseEntity<ResponseDTO> changeXtremes()throws ListaDeException
    {return listsDeService.changeXtremes();}

    @GetMapping(path = "delete/{identification}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String identification) throws ListaDeException
    {return listsDeService.delete(identification);}

    @GetMapping(path = "variant")
    public ResponseEntity<ResponseDTO> variantList() throws ListaDeException
    {return listsDeService.variantList();}

    @GetMapping(path = "boysbylocation")
    public ResponseEntity<ResponseDTO> boysByLocation()
    {return listsDeService.getBoysByLocation();}

    @GetMapping(path = "boysbylocationbyage/{age}/{location}")
    public  ResponseEntity<ResponseDTO> boysByLocationByAge(@PathVariable byte age,@PathVariable String location)
    {return listsDeService.boysByLocationByAge(age,location);}

    @GetMapping(path = "bygenderage/{age}/{gender}")
    public ResponseEntity<ResponseDTO>byGenderAge(@PathVariable byte age, @PathVariable String gender)throws ListaDeException
    {return listsDeService.byGenderAge(age, gender);}

    @GetMapping(path = "countboysbygender")
    public ResponseEntity<ResponseDTO>countBoysByGender()
    {return listsDeService.getCountBoysByGender();}

    @GetMapping(path = "deletebyage/{age}")
    public ResponseEntity<ResponseDTO>deleteByAge(@PathVariable byte age)throws ListaDeException
    {return listsDeService.deleteByAge(age);}

    @GetMapping(path = "deletebygender/{code}")
    public ResponseEntity<ResponseDTO>deleteByGender(@PathVariable String code)throws ListaDeException
    {return listsDeService.deleteByGender(code);}

    @GetMapping(path = "listboysdegree/{degree}")
    public ResponseEntity<ResponseDTO>listBoyDegree(@PathVariable Integer degree)
    {return  listsDeService.listBoysDegree(degree);}

    @GetMapping("deletebyposition/{boy}/{position}")
    public ResponseEntity<ResponseDTO>deleteByPosition(@PathVariable Boy boy, @PathVariable int position)throws ListaDeException
    {return listsDeService.deleteByPosition(boy,position);}


}
