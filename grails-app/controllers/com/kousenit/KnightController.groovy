package com.kousenit

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

import static org.springframework.http.HttpStatus.*

@CompileStatic
class KnightController {

    KnightService knightService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond knightService.list(params), model:[knightCount: knightService.count()]
    }

    def show(Long id) {
        respond knightService.get(id)
    }

    def create() {
        respond new Knight(params)
    }

    @CompileDynamic
    def save(Knight knight) {
        if (knight == null) {
            notFound()
            return
        }

        try {
            knightService.save(knight)
        } catch (ValidationException e) {
            respond knight.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'knight.label', default: 'Knight'), knight.id])
                redirect knight
            }
            '*' { respond knight, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond knightService.get(id)
    }

    @CompileDynamic
    @Transactional
    def update(Knight knight) {
        if (knight == null) {
            notFound()
            return
        }

        try {
            knightService.save(knight)
        } catch (ValidationException e) {
            respond knight.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'knight.label', default: 'Knight'), knight.id])
                redirect knight
            }
            '*'{ respond knight, [status: OK] }
        }
    }

    @CompileDynamic
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        knightService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'knight.label', default: 'Knight'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @CompileDynamic
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
