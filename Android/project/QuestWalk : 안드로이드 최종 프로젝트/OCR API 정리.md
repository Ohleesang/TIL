# OCR API 정리

## 선별 기준
- 해상도 이슈
    - 이미지의 사이즈에 따른 한계량이 있음
    - 실시간으로 보여줄때 최적화를 해야함
- 인식률 이슈
    - 무료 openAPI의 경우 인식률이 저조한 경우도 보임
    - 이로인해 전처리과정이 필요함(이미지 사이즈를 줄인다거나, 필터를 줘서 인식률을 높인다거나)
- 가격책정 이슈
    - 가격 책정에 있어서 일단위가아닌 월단위로 측정됨
    - 서비스를 운영 하는경우에 있어서 손익분기점에 따라 조율을 해야함
- 안드로이드 호환 이슈
    - 무료 openAPI 의경우, 대부분 파이썬 기반 라이브러리가 많음
    - 이로인해, 안드로이드에 직접적으로 받을수 없고 따로 서버를 구성해야한다고함


## API 정리
1. OCR Space API 
    - 월 25000건 무료
        - 유료로 전환시 정액제로 지불(제한조건이 있지만)
            - 조사한 api 중에 가격이 그나마 합리적으로 보임
        - 인식률 나쁘지않음 하지만 무료의 경우 1MB 정도에서 해상도의 타격이 큼
        
2. **Tesseract와 EasyOCR, PaddingOCR**
    - 무료 API
        - 인식률이 유료 API에 비해 아쉬움이 많음(PaddingOCR은 제외)
            - 이로인해 전처리과정이 필수적
    - Python 기반 라이브러리
        - 안드로이드에서 직접 불러서 쓸수없음
        - 서버를 구축해서 받아오는 형식으로 구현해야함
    
3. Azure Document Intelligence
    - 유료 API
        - 월 500건은 무료 이후에는 건당 0.0'1 $ 
    - ~~자료가 부족함,대중적이지 않은듯~~
    
4. Naver Clova
    - 유료 API
        - 월 100건 무료 이후 건당 3원
    - 한글 인식률이 제일 좋은듯
    - 클라우드 서비스로 지원

5. Google Vision API(ML KIT)
    - 유료 API
        - 월 1000건 무료 이후 건당 $1.5
    - 한글 인식률 괜찮음
    - **Firebase ML Kit 를 이용한 클라우드 서비스**
    - 자료가 풍부함, 유튜브에 OCR 튜토리얼 검색하면 대부분 사용하여 설명


## 결론
무료 API 를 이용할려면 OCR SPACE API 를 이용하는게 가장 좋아보이고, 유료 API를 이용할려면 Google Vision API 를 이용 하는게 나아보임

[OCR SPACE API](https://ocr.space/ocrapi)

[Google Vision API(ML KIT)](https://cloud.google.com/vision/docs/drag-and-drop?hl=ko)