package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.CategoryDTO;
import br.edu.utfpr.pb.pw44s.server.model.Category;
import br.edu.utfpr.pb.pw44s.server.service.ICategoryService;
import br.edu.utfpr.pb.pw44s.server.service.ICrudService;
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
public class CategoryController extends CrudController<Category, CategoryDTO, Long> {
    private static ICategoryService categoryService;
    private static ModelMapper modelMapper;

    public CategoryController(ICategoryService categoryService,
                              ModelMapper modelMapper) {
        super(Category.class, CategoryDTO.class);
        CategoryController.categoryService = categoryService;
        CategoryController.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Category, Long> getService() {
        return CategoryController.categoryService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return CategoryController.modelMapper;
    }
}
