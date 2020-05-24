package com.mtjin.cnunoticeapp.data.employ.source.remote

import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Observable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class EmployNoticeRemoteDataSourceImpl : EmployNoticeRemoteDataSource {
    override fun requestNotice(): Single<List<EmployNotice>> {
        return Single.fromObservable(
            Observable.create {
                val noticeList: ArrayList<EmployNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/job.do")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    noticeList.add(
                        EmployNotice(
                            idElements[i].text(),
                            elem.text(),
                            "https://computer.cnu.ac.kr/computer/notice/job.do" + elem.attr("href")
                        )
                    )
                }
                it.onNext(noticeList)
                it.onComplete()
            }
        )
    }

    override fun requestMoreNotice(offset: Int): Single<List<EmployNotice>> {
        return Single.fromObservable(
            Observable.create {
                val noticeList: ArrayList<EmployNotice> = ArrayList()
                val doc: Document =
                    Jsoup.connect("https://computer.cnu.ac.kr/computer/notice/job.do?mode=list&&articleLimit=10&article.offset=$offset")
                        .get() // Base Url
                val contentElements: Elements =
                    doc.select("div[class=b-title-box]").select("a") // title, link
                val idElements: Elements = doc.select("td[class=b-num-box]") // id값
                for ((i, elem) in contentElements.withIndex()) {
                    if (idElements[i].text() != "공지") { // 공지는 매 페이지마다 있으므로 중복제거
                        noticeList.add(
                            EmployNotice(
                                idElements[i].text(),
                                elem.text(),
                                "https://computer.cnu.ac.kr/computer/notice/job.do" + elem.attr("href")
                            )
                        )
                    }
                }
                it.onNext(noticeList)
                it.onComplete()
            }
        )
    }

}