/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safi.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.safi.entity.EstadoCuentaBancaria;
import com.safi.utilidad.Utilidad;

/**
 *
 * @author Angel Alvarenga
 */
@Stateless
public class EstadoCuentaBancariaFacade extends AbstractFacade<EstadoCuentaBancaria> implements EstadoCuentaBancariaFacadeLocal {

    @PersistenceContext(unitName = "EAppSAFI-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCuentaBancariaFacade() {
        super(EstadoCuentaBancaria.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<EstadoCuentaBancaria> findAll(boolean estado) {
        Query consulta = em.createQuery("select object(o) from EstadoCuentaBancaria as o WHERE o.estado = :p1 order by o.nombre");
        consulta.setParameter("p1", estado);
        return consulta.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<EstadoCuentaBancaria> findAll(String nombre) {
        StringBuilder query = new StringBuilder();
        query.append("select object(o) FROM EstadoCuentaBancaria as o WHERE o.estado = true ");
        if (nombre != null) {
            query.append(" AND upper(FUNCTION('unaccent', o.nombre)) LIKE '%");
            query.append(Utilidad.desacentuar(nombre.toUpperCase()));
            query.append("%' ");
        }
        query.append("ORDER BY o.nombre");
        Query consulta = em.createQuery(query.toString());
        return consulta.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<EstadoCuentaBancaria> findAll(String nombre, int first, int pageSize) {
        StringBuilder query = new StringBuilder();
        query.append("select object(o) FROM EstadoCuentaBancaria as o WHERE o.estado = true ");
        if (nombre != null && !nombre.isEmpty()) {
            query.append(" AND upper(FUNCTION('unaccent', o.nombre)) LIKE '%");
            query.append(Utilidad.desacentuar(nombre.toUpperCase()));
            query.append("%' ");
        }
        query.append("ORDER BY o.nombre");
        Query consulta = em.createQuery(query.toString());
        consulta.setFirstResult(first);
        consulta.setMaxResults(pageSize);
        return consulta.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public Long countAll(String nombre) {
        StringBuilder query = new StringBuilder();
        query.append("select COUNT(o) FROM EstadoCuentaBancaria as o WHERE o.estado = true ");
        if (nombre != null && !nombre.isEmpty()) {
            query.append(" AND upper(FUNCTION('unaccent', o.nombre)) LIKE '%");
            query.append(Utilidad.desacentuar(nombre.toUpperCase()));
            query.append("%' ");
        }
        Query consulta = em.createQuery(query.toString());
        return (Long) consulta.getSingleResult();
    }

    @Override
    public void create(String nombre) throws Exception {
        try {
            EstadoCuentaBancaria estadoCuentaBancariaAux = new EstadoCuentaBancaria();
            estadoCuentaBancariaAux.setNombre(nombre.toUpperCase());
            estadoCuentaBancariaAux.setEstado(true);
            this.create(estadoCuentaBancariaAux);
        } catch (Exception e) {
            throw new Exception("Error al intentar crear el Estado de la Cuenta Bancaria");
        }
    }

    @Override
    public void edit(Long idEstadoCuentaBancaria, String nombre) throws Exception {
        try {
            EstadoCuentaBancaria estadoCuentaBancariaAux = this.find(idEstadoCuentaBancaria);
            estadoCuentaBancariaAux.setNombre(nombre.toUpperCase());
            this.edit(estadoCuentaBancariaAux);
        } catch (Exception e) {
            throw new Exception("Error al intentar editar el Estado de la Cuenta Bancaria");
        }
    }

    @Override
    public void remove(Long idEstadoCuentaBancaria) throws Exception {
        try {
            EstadoCuentaBancaria estadoCuentaBancariaAux = this.find(idEstadoCuentaBancaria);
            estadoCuentaBancariaAux.setEstado(false);
            this.edit(estadoCuentaBancariaAux);
        } catch (Exception e) {
            throw new Exception("Error al intentar borrar el Estado de la Cuenta Bancaria");
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<EstadoCuentaBancaria> findByName(String nombre) {
        StringBuilder query = new StringBuilder();
        query.append("select object(o) FROM EstadoCuentaBancaria as o WHERE o.estado = true");
        if (!(nombre.equals(""))) {
            query.append(" and upper(FUNCTION('unaccent', o.nombre)) LIKE '").append(Utilidad.desacentuar(nombre).toUpperCase()).append("'");
        }
        query.append(" ORDER BY o.nombre ASC");
        Query consulta = em.createQuery(query.toString());
        return consulta.getResultList();
    }

}
