package org.zerock.apiserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
    페이징 결과물 목록 데이터
*/
@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;
    private List<Integer> pageNumberList;
    //검색 조건 등
    private PageRequestDTO pageRequestDTO;
    private boolean prev, next;
    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) total;

        // 끝페이지 계산
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

        // 시작 페이지 계산
        int start = end - 9;
        
        // 진짜 마지막 페이지
         int realEnd =(int)(Math.ceil(totalCount / (double) pageRequestDTO.getSize()));

        end = end > realEnd ? realEnd : end;

        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDTO.getSize();
        /*
        *  101 (totalCount) > (10 * 10 = 100) = true
        * */
        this.pageNumberList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start -1 : 0;
        this.nextPage = next ? end + 1 : 0;
    }
}
/*
*   페이징 계산
*
*   ex) size = 10 일떄
*   1 ...... 10 ➡️
*   ⬅️ 11 ...... 20 ➡️
*
*   $ ➡️ (next)
*   $ 21 (nextPage)
*
*   $ ⬅️ (prev)
*   $ 10 (prevPage)
*
*   $ 현재 page 3
*   시작 페이지 번호 | 끝 번호
*           1     |   10
*
*         1. 끝번호 구하기
*           1) 나누기 : 3 / 10.0 = 0.3
*           2) 올림 : 1
*           3) 곱하기 : 1 * 10 = 10
*
*         2. 시작번호 구하기
*           1) 10(끝번호) - 9 = 1
*
*   $ 현재 page 12
*   시작 페이지 번호 | 끝 번호
            11     |   20

         1. 끝 번호 구하기
            1) 나누기 :  12(현재 page) / 10.0 = 1.2
            2) 올림  :  2
            3) 곱하기 : 2 * 10(단위) = 20

         2. 시작 번호
            1) 20(끝 번호) - 9 = 11

*   $ 현재 page 10
*   시작 페이지 번호 | 끝 번호
            1     |   10

            1. 끝 번호 구하기
                1) 나누기 : 10 / 10.0 = 0.1
                2) 올림 : 1
                3) 곱하기 : 1 * 10 = 10

            2. 시작 번호 구하기
                1) 10(끝번호) -9 = 1
*
    * $ 현재 page 11
*   시작 페이지 번호 | 끝 번호
           11     |   20

           1. 끝 번호 구하기
                1) 나누기 : 11 / 10.0 = 1.1
                2) 올림 : 2
                3) 곱하기 : 2 * 10 = 20
           2. 시작 번호 구하기
                1) 20(끝번호) - 9 = 11

*   $$$ 진짜 마지막 페이지 구하기

     $ 현재 page 8
*   시작 페이지 번호 | 끝 번호
           1     |   10

           1. 끝 번호 구하기
                1) 나누기 : 8 / 10.0 = 0.8
                2) 올림 : 1
                3) 곱하기 : 1 * 10 = 10
           2. 시작 번호 구하기
                1) 10(끝번호) - 9 = 1
*
     $ 데이터가 78개 인경우
        => 8page 까지 보여줘야한다.

        나누기 : 78(total) / 10.0(페이지당 10개) = 7.8
        올림 : 8

        비교 :
        *  10(끝페이지) > 8 (실제 페이지)
            end = 실제 페이지
         
        
      $ 데이터가 300개 인경우 
        5 page를 보고 있을 때

        나누기 : 300(total) / 10.0(페이지당 10개) = 30
        올림 : 30

        비교 :
        *  10(끝페이지) > 30 (실제 페이지)
            end = 실제 페이지
        else
            end = 끝페이지(10)


* */