package com.kina.ex2.repository;

import com.kina.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    void testClass() {

        System.out.println(memoRepository.getClass().getName());

    }

    @Test
    void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample..." + i)
                    .build();

            memoRepository.save(memo);
        });

    }

    @Test
    void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("========================================");

        if (result.isPresent()) {
            Memo memo = result.get();

            System.out.println(memo);
        }

    }

    @Transactional
    @Test
    void testSelect2() {

        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("========================================");

        System.out.println(memo);

    }

    @Test
    void testUpdate() {

        Memo memo = Memo.builder()
                .mno(100L)
                .memoText("Update Text")
                .build();

        System.out.println(memoRepository.save(memo));

    }

    @Test
    void testDelete() {

        Long mno = 100L;

        memoRepository.deleteById(mno);

    }

    @Test
    void testPageDefault() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("========================================");

        System.out.println("Total Pages: " + result.getTotalPages());

        System.out.println("Total Count: " + result.getTotalElements());

        System.out.println("Page Number: " + result.getNumber());

        System.out.println("Page Size: " + result.getSize());

        System.out.println("has next page? " + result.hasNext());

        System.out.println("first page? " + result.isFirst());

        result.getContent().forEach(System.out::println);

    }

    @Test
    void testSort1() {

        Sort sort = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(System.out::println);

    }

    @Test
    void testSort2() {

        Sort sort1 = Sort.by("mno").descending();

        Sort sort2 = Sort.by("memoText").ascending();

        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(System.out::println);

    }

    @Test
    void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        list.forEach(System.out::println);

    }

    @Test
    void testQueryMethodWithPageable() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(System.out::println);

    }

    @Commit
    @Transactional
    @Test
    void testDeleteQueryMethods() {

        memoRepository.deleteMemoByMnoLessThan(10L);
        
    }

}
