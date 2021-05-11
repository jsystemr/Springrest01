package com.siguasystem;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siguasystem.repository.PreciosmercadosRepository;
import com.siguasystem.model.*;

@RestController
public class PreciomercadoController {

	Logger log=LoggerFactory.getLogger(getClass().getName());
	
	@Autowired
	private PreciosmercadosRepository jprecio;
	
	@GetMapping("/preciosmercado")
	public List<Preciomercado> all(){
		return jprecio.findAll();
	}
	
	@RequestMapping(path="/preciosmercado/new",method = RequestMethod.POST)//(path="/preciosmercado/new",consumes = "application/json", produces = "application/json")
	public Preciomercado preciomercado(@RequestBody Preciomercado pre) {
		log.info("Registro Recibido:"+pre.getFecharegistro());
		return jprecio.save(pre);
	}
	
	@PostMapping("/preciosmercado/new2")
	public Preciomercado preciomercado(@RequestParam("nombreproducto") String producto,@RequestParam("precio") String precio,@RequestParam("pais") String pais) {
		Preciomercado p=new Preciomercado();
		p.setNombreproducto(producto);p.setPrecio(Double.parseDouble(precio));
		p.setPais(pais);
		return jprecio.save(p);
	}
	
	@GetMapping("/preciomercados/{id}")
	public Preciomercado getPrecio(@PathVariable Long id) {
		return jprecio.findById(id).get();
	}
	
	//@PutMapping ("/preciomercados/{id}")
	@RequestMapping(value = "/preciomercados/{id}", 
			  produces = "application/json", 
			  method=RequestMethod.PUT)
	public Preciomercado updatePrecio(@RequestBody Preciomercado pre,@PathVariable Long id) {
		Preciomercado recibido=jprecio.getOne(id);
		recibido.setNombreproducto(pre.getNombreproducto());recibido.setPrecio(pre.getPrecio());
		recibido.setCiudad(pre.getCiudad());recibido.setDepartamento(pre.getDepartamento());
		recibido.setNegociolocal(pre.getNegociolocal());
		return jprecio.save(recibido);
	}
	
	@DeleteMapping("/preciosmercados/{id}")
	public void delprecios(@PathVariable Long id) {
		jprecio.deleteById(id);
		}
	
	
}
