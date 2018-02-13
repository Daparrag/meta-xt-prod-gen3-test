do_install() {
    # Install header files
    install -d ${D}/${includedir}/EGL
    install -m 644 ${S}/include/khronos/EGL/*.h ${D}/${includedir}/EGL/
    install -d ${D}/${includedir}/GLES2
    install -m 644 ${S}/include/khronos/GLES2/*.h ${D}/${includedir}/GLES2/
    install -d ${D}/${includedir}/GLES3
    install -m 644 ${S}/include/khronos/GLES3/*.h ${D}/${includedir}/GLES3/
    install -d ${D}/${includedir}/KHR
    install -m 644 ${S}/include/khronos/KHR/khrplatform.h ${D}/${includedir}/KHR/khrplatform.h
    install -m 644 ${S}/include/khronos/drv/EGL/eglext_REL.h ${D}/${includedir}/EGL/
}

