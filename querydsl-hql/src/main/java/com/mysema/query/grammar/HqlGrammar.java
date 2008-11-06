/*
 * Copyright (c) 2008 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.grammar;

import static com.mysema.query.grammar.types.Factory.createBoolean;
import static com.mysema.query.grammar.types.Factory.createComparable;
import static com.mysema.query.grammar.types.Factory.createNumber;

import java.util.Collection;
import java.util.Date;

import com.mysema.query.grammar.HqlOps.HqlPathType;
import com.mysema.query.grammar.HqlOps.OpHql;
import com.mysema.query.grammar.HqlOps.OpNumberAgg;
import com.mysema.query.grammar.HqlOps.OpQuant;
import com.mysema.query.grammar.types.CollectionType;
import com.mysema.query.grammar.types.Expr;
import com.mysema.query.grammar.types.Path;
import com.mysema.query.grammar.types.PathMetadata;
import com.mysema.query.grammar.types.Quant;
import com.mysema.query.grammar.types.HqlTypes.Constructor;
import com.mysema.query.grammar.types.HqlTypes.CountExpression;
import com.mysema.query.grammar.types.HqlTypes.DistinctPath;
import com.mysema.query.grammar.types.HqlTypes.SubQuery;

/**
 * HqlGrammar extends the Query DSL base grammar to provide HQL specific syntax elements.
 * 
 * @author tiwe
 * @version $Id$
 */
public class HqlGrammar extends Grammar{
            
    public static <D> Expr<D> all(CollectionType<D> col){
        return new Quant.Simple<D>(OpQuant.ALL, col);
    }    
    public static <D extends Comparable<D>> Expr.Comparable<D> all(CollectionType<D> col){
        return new Quant.Comparable<D>(OpQuant.ALL, col);
    }
    
    public static <D> Expr.Simple<D> any(CollectionType<D> col){
        return new Quant.Simple<D>(OpQuant.ANY, col);
    }    
    public static <D extends Comparable<D>> Expr.Comparable<D> any(CollectionType<D> col){
        return new Quant.Comparable<D>(OpQuant.ANY, col);
    }    
    
    public static <A extends Comparable<A>> Expr.Comparable<A> avg(Expr<A> left){
        return createNumber(OpNumberAgg.AVG, left);
    }    
    public static <A extends Comparable<A>> Expr.Comparable<A> avg(Path.Collection<A> left){
        return new Quant.Comparable<A>(OpQuant.AVG_IN_COL, left);
    }        
    
    public static Expr.Comparable<Long> count(){
        return new CountExpression(null);
    }
    
    public static Expr.Comparable<Long> count(Expr<?> expr){
        return new CountExpression(expr);
    }
    public static Expr.Comparable<Date> current_date(){
        return createComparable(OpHql.CURRENT_DATE);
    }    
    public static Expr.Comparable<Date> current_time(){
        return createComparable(OpHql.CURRENT_TIME);
    }    
    public static Expr.Comparable<Date> current_timestamp(){
        return createComparable(OpHql.CURRENT_TIMESTAMP);
    }
    
    public static Expr.Comparable<Date> day(Expr<Date> date){
        return createComparable(OpHql.DAY, date);
    }    
    
    public static <T> Expr<T> distinct(Path<T> left){
        return new DistinctPath<T>(left);
    }    
    
    public static <D> Expr.Boolean exists(CollectionType<D> col){
        return new Quant.Boolean<D>(OpQuant.EXISTS, col);
    }
    
    public static <A> SubQuery<A> from(Expr.Entity<A> select){
        return new SubQuery<A>(select).from(select);
    }
    
    public static Expr.Comparable<Date> hour(Expr<Date> date){
        return createComparable(OpHql.HOUR, date);
    }  

    public static Path.ComponentCollection<Integer> indices(Path.Collection<?> col){
        return new Path.ComponentCollection<Integer>(Integer.class, new PathMetadata<Collection<Integer>>(col, null, HqlPathType.LISTINDICES));
    }
    
    public static <K,V> Path.ComponentCollection<K> indices(Path.Map<K,V> col){
        return new Path.ComponentCollection<K>(col.getKeyType(), new PathMetadata<Collection<Integer>>(col, null, HqlPathType.LISTINDICES));
    }
    
    public static Expr.Boolean isempty(Path.ComponentCollection<?> collection) {
        return createBoolean(OpHql.ISEMPTY, collection);        
    }
               
    public static Expr.Boolean isempty(Path.EntityCollection<?> collection) {
        return createBoolean(OpHql.ISEMPTY, collection);        
    }
    
    public static Expr.Boolean isnotempty(Path.ComponentCollection<?> collection) {
        return createBoolean(OpHql.ISNOTEMPTY, collection);        
    }
    
    public static Expr.Boolean isnotempty(Path.EntityCollection<?> collection) {
        return createBoolean(OpHql.ISNOTEMPTY, collection);        
    }
    
    public static <A extends Comparable<A>> Expr.Comparable<A> max(Expr<A> left){
        return createNumber(OpNumberAgg.MAX, left);
    }
    
    public static <A extends Comparable<A>> Expr.Comparable<A> max(Path.Collection<A> left){
        return new Quant.Comparable<A>(OpQuant.MAX_IN_COL, left);
    } 
    
    public static <A> Path.Entity<A> maxelement(Path.EntityCollection<A> col) {
        return new Path.Entity<A>(col.getElementType(), new PathMetadata<A>(col, null, HqlPathType.MINELEMENT));
    }
    
    public static <A> Path.Comparable<Integer> maxindex(Path.ComponentCollection<A> col) {
        return new Path.Comparable<Integer>(Integer.class, new PathMetadata<Integer>(col, null, HqlPathType.MAXINDEX));
    }
    
    public static <A> Path.Comparable<Integer> maxindex(Path.EntityCollection<A> col) {
        return new Path.Comparable<Integer>(Integer.class, new PathMetadata<Integer>(col, null, HqlPathType.MAXINDEX));
    }  
    
    public static <A extends Comparable<A>> Expr.Comparable<A> min(Expr<A> left){
        return createNumber(OpNumberAgg.MIN, left);
    }
    
    public static <A extends Comparable<A>> Expr.Comparable<A> min(Path.Collection<A> left){
        return new Quant.Comparable<A>(OpQuant.MIN_IN_COL, left);
    }       
    
    public static <A> Path.Entity<A> minelement(Path.EntityCollection<A> col) {
        return new Path.Entity<A>(col.getElementType(), new PathMetadata<A>(col, null, HqlPathType.MINELEMENT));
    } 
    
    public static <A> Path.Comparable<Integer> minindex(Path.ComponentCollection<A> col) {
        return new Path.Comparable<Integer>(Integer.class, new PathMetadata<Integer>(col, null, HqlPathType.MININDEX));
    }
    
    public static <A> Path.Comparable<Integer> minindex(Path.EntityCollection<A> col) {
        return new Path.Comparable<Integer>(Integer.class, new PathMetadata<Integer>(col, null, HqlPathType.MININDEX));
    }        
    
    public static Expr.Comparable<Date> minute(Expr<Date> date){
        return createComparable(OpHql.MINUTE, date);
    }    
    
    public static Expr.Comparable<Date> month(Expr<Date> date){
        return createComparable(OpHql.MONTH, date);
    }
    
    public static <A> Expr<A> newInstance(Class<A> a, Expr<?>... args){
        return new Constructor<A>(a,args);
    }
    
    public static <D> Expr.Boolean notExists(CollectionType<D> col){
        return new Quant.Boolean<D>(OpQuant.NOTEXISTS, col);
    }
        
    public static Expr.Comparable<Date> second(Expr<Date> date){
        return createComparable(OpHql.SECOND, date);
    }
    
    public static <A> SubQuery<A> select(Expr<A> select){
        return new SubQuery<A>(select);
    }
    
    public static <D> Expr<D> some(CollectionType<D> col){
        return any(col);
    }
        
    public static <D extends Comparable<D>> Expr.Comparable<D> sum(Expr<D> left){ 
        return createNumber(OpHql.SUM, left);
    }
    
    public static Expr.Comparable<Date> sysdate(){
        return createComparable(OpHql.SYSDATE);
    }
    
    public static Expr.Comparable<Date> year(Expr<Date> date){
        return createComparable(OpHql.YEAR, date);
    }
              
}
