package com.mtjin.cnunoticeapp.data.general.source.remote

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Observable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class GeneralNoticeRemoteDataSourceImpl : GeneralNoticeRemoteDataSource {
    override fun requestNotice(): Single<List<GeneralNotice>> {
        return Single.fromObservable(
            Observable.create {
                val generalNoticeList: ArrayList<GeneralNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/notice.do")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    generalNoticeList.add(
                        GeneralNotice(
                            idElements[i].text(),
                            elem.text(),
                            "https://computer.cnu.ac.kr/computer/notice/notice.do" + elem.attr("href")
                        )
                    )
                }
                it.onNext(generalNoticeList)
                it.onComplete()
            }
        )
    }

    override fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>> {
        return Single.fromObservable(
            Observable.create {
                val generalNoticeList: ArrayList<GeneralNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/notice.do?mode=list&&articleLimit=10&article.offset=$offset")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    if (idElements[i].text() != "공지") { // 공지는 매 페이지마다 있으므로 중복제거
                        generalNoticeList.add(
                            GeneralNotice(
                                idElements[i].text(),
                                elem.text(),
                                "https://computer.cnu.ac.kr/computer/notice/notice.do" + elem.attr("href")
                            )
                        )
                    }
                }
                it.onNext(generalNoticeList)
                it.onComplete()
            }
        )
    }

}