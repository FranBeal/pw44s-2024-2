package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.CategoryDTO;
import br.edu.utfpr.pb.pw44s.server.model.Category;
import br.edu.utfpr.pb.pw44s.server.service.ICategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final ModelMapper modelMapper;

    //É realizada a injeção de dependência do service de categoria (ICategoryService),
    // pois é por meio dele que serão realizadas as operações com ligação ao
    // banco de dados
    public CategoryController(ICategoryService categoryService,
                              ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    // O Metodo findAll() retorna uma lista de categorias quando
    // é executando quando é realizada uma requisição do tipo GET para:
    // http://localhost:8025/categories
    // e o retorno será um json no formato:
    // [{id:1, name: "Category One"}, {...}, ...]
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        //Este metodo Retorna uma resposta HTTP com o status 200 OK e
        //o corpo contendo a lista de categorias em formato JSON
        return ResponseEntity.ok(categoryService.findAll());
    }

    // O Metodo create() é executando quando é realizada uma
    // requisição do tipo POST para:
    // http://localhost:8025/categories
    // Recebe como parâmetro um objeto JSON no formato:
    // {id: null, name: "Category One"}
    // e o retorno será um json no formato:
    // {id:1, name: "Category One"}
    // Ou seja a categoria com o ID gerado ao armazenar o registro no banco de dados
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryDTO category) {
        //@RequestBody @Valid Category category: Indica que o corpo da requisição
        // deve ser desserializado para um objeto Category e validado conforme as
        // anotações de validação presentes na classe Category
        Category categoryEntity = modelMapper.map(category, Category.class);
        categoryService.save(categoryEntity);

        //Constrói a URI do recurso recém-criado, por exemplo,
        // http://localhost:8025/categories/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand( category.getId() ).toUri();

        //Retorna uma resposta com o status 201 Created, adiciona o
        // cabeçalho Location com a URI do novo recurso e inclui a
        // categoria criada no corpo da resposta
        return ResponseEntity.created( location ).body( categoryEntity );
    }

    // O Metodo findOne() é executando quando é realizada uma
    // requisição do tipo GET para:
    // http://localhost:8025/categories/1
    // onde o 1 é o ID da categoria que deverá ser retornada,
    //e o retorno será um json no formato:
    // {id:1, name: "Category One"}
    @GetMapping("{id}") //http://localhost:8025/categories/1
    public ResponseEntity<Category> findOne(@PathVariable("id") Long id) {
        //@PathVariable("id") Long id: Extrai o valor do parâmetro de caminho {id} e
        // o utiliza como argumento do metodo
        return ResponseEntity.ok(categoryService.findOne(id));
    }

    // O Metodo delete() é executando quando é realizada uma
    // requisição do tipo DELETE para:
    // http://localhost:8025/categories/1
    // onde o 1 é o ID da categoria que deverá ser removida,
    //e o retorno, no caso de sucesso será
    //um corpo de requisição vazio com o status:
    // 204 - NO CONTENT
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Define que a resposta terá o status 204 No Content
    // sem corpo.
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    //Recupera Categorias com Paginação
    @GetMapping("page") //Mapeia requisições HTTP GET para GET /categories/page
    // http://localhost:8025/categories/page?page=0&size=5&order=name&asc=true
    public ResponseEntity<Page<Category>> findAllPaged(
            //@RequestParam: Extrai parâmetros de consulta da URL
            @RequestParam int page, //Número da página (inicia em 0)
            @RequestParam int size, //Quantidade de itens por página
            @RequestParam(required = false) String order,//Campo pelo qual ordenar
            @RequestParam(required = false) Boolean asc //Define se a ordenação é ascendente (true) ou descendente (false)
    ) {
        //Cria um objeto de requisição de página com os parâmetros fornecidos
        PageRequest pageRequest = PageRequest.of(page, size);
        if (order != null && asc != null) {
            //Sort.Direction.ASC/DESC: Define a direção da ordenação com base no parâmetro asc
            pageRequest = PageRequest.of(page, size,
                    asc ? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        //Obtém uma página de categorias conforme os parâmetros de paginação e ordenação
        return ResponseEntity.ok(categoryService.findAll(pageRequest));
    }

}
