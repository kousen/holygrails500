package com.kousenit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestController {

    QuestService questService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond questService.list(params), model:[questCount: questService.count()]
    }

    def show(Long id) {
        respond questService.get(id)
    }

    def create() {
        respond new Quest(params)
    }

    def save(Quest quest) {
        if (quest == null) {
            notFound()
            return
        }

        try {
            questService.save(quest)
        } catch (ValidationException e) {
            respond quest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quest.label', default: 'Quest'), quest.id])
                redirect quest
            }
            '*' { respond quest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questService.get(id)
    }

    def update(Quest quest) {
        if (quest == null) {
            notFound()
            return
        }

        try {
            questService.save(quest)
        } catch (ValidationException e) {
            respond quest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quest.label', default: 'Quest'), quest.id])
                redirect quest
            }
            '*'{ respond quest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quest.label', default: 'Quest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
