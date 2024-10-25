package br.edu.utfpr.pb.pw44s.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

//Interface genérica chamada ICrudService<T, ID extends Serializable>, que
// define metodos para operações comuns de CRUD (Create, Read, Update, Delete)
// em uma entidade genérica T com um identificador ID.
//Onde T representa o tipo da entidade e ID representa o tipo do identificador
// da entidade, que deve ser serializável (deve implementar Serializable).
// Isso é importante para garantir que o identificador possa ser transmitido
// em processos de serialização, como em transações distribuídas ou
// comunicação entre sistemas.
public interface ICrudService <T, ID extends Serializable>{
    List<T> findAll();
    List<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
    T save(T entity);

    //Similar a save, mas garante que as alterações sejam imediatamente sincronizadas
    //com o banco de dados, forçando o flush após a operação
    T saveAndFlush(T entity);
    //Permite salvar múltiplas entidades de uma vez, recebendo um Iterable com
    // várias instâncias de T
    Iterable<T> save(Iterable<T> iterable);

    //Força a sincronização das mudanças pendentes com o banco de dados.
    // Usado para garantir que as operações sejam efetivadas imediatamente.
    void flush();
    T findOne(ID id);
    boolean exists(ID id);
    long count();
    void delete(ID id);
    void delete(Iterable<? extends T> iterable);
    void deleteAll();


}
