package com.makagonov.orders.business.repository

import com.makagonov.orders.business.model.Station
import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository : JpaRepository<Station, Long>
