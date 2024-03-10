#include <jni.h>
#include <string>
#include <GLES3/gl3.h>

// Вершины прямоугольника
GLfloat vertices[] = {
        // Первый треугольник
        -0.5f, -0.5f, 0.0f, // нижний левый угол
        0.5f, -0.5f, 0.0f, // нижний правый угол
        -0.5f,  0.5f, 0.0f, // верхний левый угол
        // Второй треугольник
        0.5f, -0.5f, 0.0f, // нижний правый угол
        0.5f,  0.5f, 0.0f, // верхний правый угол
        -0.5f,  0.5f, 0.0f  // верхний левый угол
};

GLuint VBO, VAO;

extern "C"
JNIEXPORT void JNICALL
Java_github_xxfrom_plantain_1opengl_NativeLib_initOpenGL(JNIEnv *env, jobject thiz) {
    glGenBuffers(1, &VBO);
    glGenVertexArrays(1, &VAO);

    // Привязка VAO
    glBindVertexArray(VAO);

    // Копирование вершин в буфер для OpenGL
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

    // Настройка атрибутов вершин
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
    glEnableVertexAttribArray(0);

    // Отвязка VAO
    glBindVertexArray(0);
}

extern "C"
JNIEXPORT void JNICALL
Java_github_xxfrom_plantain_1opengl_NativeLib_drawFrame(JNIEnv *env, jobject thiz) {
    // Очистка экрана
    glClearColor(255.0f, 255.0f, 0.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
}
