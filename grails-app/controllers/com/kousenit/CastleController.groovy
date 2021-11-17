package com.kousenit

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

import static org.springframework.http.HttpStatus.*

@CompileStatic
class CastleController {

    CastleService castleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond castleService.list(params), model:[castleCount: castleService.count()]
    }

    def show(Long id) {
        respond castleService.get(id)
    }

    def create() {
        respond new Castle(params)
    }

    @CompileDynamic
    def save(Castle castle) {
        if (castle == null) {
            notFound()
            return
        }

        try {
            castleService.save(castle)
        } catch (ValidationException e) {
            respond castle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'castle.label', default: 'Castle'), castle.id])
                redirect castle
            }
            '*' { respond castle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond castleService.get(id)
    }

    @CompileDynamic
    @Transactional
    def update(Castle castle) {
        if (castle == null) {
            notFound()
            return
        }

        try {
            castleService.save(castle)
        } catch (ValidationException e) {
            respond castle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'castle.label', default: 'Castle'), castle.id])
                redirect castle
            }
            '*'{ respond castle, [status: OK] }
        }
    }

    @CompileDynamic
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        castleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'castle.label', default: 'Castle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @CompileDynamic
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'castle.label', default: 'Castle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
