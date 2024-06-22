package com.framallo90.Interfaces;

/**
 * Interfaz que define operaciones básicas para la manipulación de objetos en un repositorio.
 *
 * @param <T>  Tipo del objeto que se almacenará en el repositorio.
 * @param <ID> Tipo del identificador único utilizado para buscar y manipular objetos en el repositorio.
 */
public interface IRepository<T, ID> {

    /**
     * Agrega un objeto al repositorio.
     *
     * @param object Objeto a agregar.
     */
    void add(T object);

    /**
     * Elimina un objeto del repositorio dado su identificador único.
     *
     * @param id Identificador único del objeto a eliminar.
     * @throws Exception Cuando ocurre un error durante la eliminación del objeto.
     */
    void remove(ID id) throws Exception;

    /**
     * Actualiza un objeto en el repositorio dado su identificador único.
     *
     * @param id Identificador único del objeto a actualizar.
     * @throws Exception Cuando ocurre un error durante la actualización del objeto.
     */
    void update(ID id) throws Exception;

    /**
     * Busca y devuelve un objeto del repositorio dado su identificador único.
     *
     * @param id Identificador único del objeto a buscar.
     * @return El objeto encontrado o null si no se encuentra ningún objeto con el ID especificado.
     */
    T find(ID id);
}
